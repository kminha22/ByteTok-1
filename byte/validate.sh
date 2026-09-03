#!/usr/bin/env bash

# ==========================================
# 실행 모드 설정 (1: 구조 검증만, 2: javap 검증만, all: 둘 다 연속 실행)
# 사용예시: 
#   Screen 그룹 A: MODE=1 ./run_validate.sh
#   Screen 그룹 B: MODE=2 ./run_validate.sh
# ==========================================
MODE="${MODE:-all}"

CLASSLIST_DIR="/data2/minha/data/classlist"
OUTBASE="/data2/minha/data/output"

SCRIPT_STRUCT="validate_struct.py"
SCRIPT_JAVAP="validate_javap.py"

LOG_STRUCT="failed_struct_log.txt"
LOG_JAVAP="failed_javap_log.txt"

# ✅ 검증 모드별 할당 기록 파일
PROCESSED_STRUCT_LOG="processed_lists_struct.txt"
PROCESSED_JAVAP_LOG="processed_lists_javap.txt"

# 기록 파일이 없으면 생성
touch "$PROCESSED_STRUCT_LOG" "$PROCESSED_JAVAP_LOG"

echo "=========================================="
echo " Starting Validation (MODE: $MODE)"
echo " Struct Tracker Log : $PROCESSED_STRUCT_LOG"
echo " Javap  Tracker Log : $PROCESSED_JAVAP_LOG"
echo "=========================================="

# 💡 서브셸 문제를 방지하기 위해 Process Substitution (< <(...)) 사용
while read -r list_file; do
    [ -z "$list_file" ] && continue

    name=$(basename "$list_file")
    # 파일명 앞뒤 공백 제거 및 트림
    name=$(echo "$name" | xargs)

    # ----------------------------------------------------
    # 1. 이미 처리된 리스트 파일인지 검사 (Skip)
    # ----------------------------------------------------
    skip_flag=0

    if [[ "$MODE" == "1" || "$MODE" == "all" ]]; then
        if grep -Fxq "$name" "$PROCESSED_STRUCT_LOG" 2>/dev/null; then
            echo "⏩ [SKIP LIST (STRUCT)] Already assigned: $name"
            skip_flag=1
        fi
    fi

    if [[ "$MODE" == "2" ]]; then
        if grep -Fxq "$name" "$PROCESSED_JAVAP_LOG" 2>/dev/null; then
            echo "⏩ [SKIP LIST (JAVAP)] Already assigned: $name"
            skip_flag=1
        fi
    fi

    # 스킵 대상이면 다음 리스트 파일로 바로 이동
    if [ $skip_flag -eq 1 ]; then
        continue
    fi

    # ----------------------------------------------------
    # 2. 선점 기록 작성 (Locking)
    # ----------------------------------------------------
    if [[ "$MODE" == "1" || "$MODE" == "all" ]]; then
        echo "$name" >> "$PROCESSED_STRUCT_LOG"
    fi

    if [[ "$MODE" == "2" || "$MODE" == "all" ]]; then
        echo "$name" >> "$PROCESSED_LIST_LOG" 2>/dev/null || echo "$name" >> "$PROCESSED_JAVAP_LOG"
    fi

    echo -e "\n=========================================="
    echo "🔒 [ASSIGNED (MODE: $MODE)] Processing List File [$name]"
    echo "=========================================="

    # 경로 결정 조건
    if [[ $name == anon_* ]]; then
        OUTDIR="$OUTBASE/anon/$name"
    elif [[ $name == non_anon_za* ]]; then
        OUTDIR="$OUTBASE/non_anon_za/$name"
    elif [[ $name == non_anon_zb* ]]; then
        OUTDIR="$OUTBASE/non_anon_zb/$name"
    elif [[ $name == non_anon_zc* ]]; then
        OUTDIR="$OUTBASE/non_anon_zc/$name"
    elif [[ $name == non_anon_zd* ]]; then
        OUTDIR="$OUTBASE/non_anon_zd/$name"
    elif [[ $name == non_anon_* ]]; then
        OUTDIR="$OUTBASE/non_anon/$name"
    else
        OUTDIR="$OUTBASE/$name"
    fi

    # ----------------------------------------------------
    # 3. 리스트 파일 내부 클래스 순차 검증
    # ----------------------------------------------------
    while IFS= read -r orig_class_path || [ -n "$orig_class_path" ]; do
        orig_class_path=$(echo "$orig_class_path" | xargs)
        [ -z "$orig_class_path" ] && continue

        class_filename=$(basename "$orig_class_path")
        json_filename="${class_filename%.class}.json"
        json_path="$OUTDIR/$json_filename"

        echo -n "Checking [$name]: $json_filename ... "

        # 파일 존재 체크
        if [ ! -f "$json_path" ]; then
            echo "❌ [JSON File Missing]"
            echo "[Missing JSON] List: $name | Path: $json_path" >> "$LOG_STRUCT"
            continue
        fi

        tmp_output=$(mktemp)

        # [STEP 1] 구조 검증 (validate_struct.py)
        if [[ "$MODE" == "1" || "$MODE" == "all" ]]; then
            python3 "$SCRIPT_STRUCT" "$json_path" > "$tmp_output" 2>&1
            struct_code=$?

            if [ $struct_code -eq 0 ]; then
                echo -n "[STRUCT: ✅] "
            else
                echo "❌ [STRUCT FAILED]"
                {
                    echo "=========================================="
                    echo "Timestamp : $(date)"
                    echo "List Name : $name"
                    echo "JSON Path : $json_path"
                    echo "---------------- Output ------------------"
                    cat "$tmp_output"
                    echo "=========================================="
                    echo ""
                } >> "$LOG_STRUCT"
                
                rm -f "$tmp_output"
                continue
            fi
        fi

        # [STEP 2] Javap & 바이트 검증 (validate_javap.py)
        if [[ "$MODE" == "2" || "$MODE" == "all" ]]; then
            if [ ! -f "$orig_class_path" ]; then
                echo "❌ [CLASS File Missing]"
                echo "[Missing Class] List: $name | Path: $orig_class_path" >> "$LOG_JAVAP"
                rm -f "$tmp_output"
                continue
            fi

            python3 "$SCRIPT_JAVAP" "$json_path" "$orig_class_path" > "$tmp_output" 2>&1
            javap_code=$?

            if [ $javap_code -eq 0 ]; then
                echo "[JAVAP: ✅]"
                cat "$tmp_output"
            else
                echo "❌ [JAVAP FAILED]"
                {
                    echo "=========================================="
                    echo "Timestamp : $(date)"
                    echo "List Name : $name"
                    echo "JSON Path : $json_path"
                    echo "Class Path: $orig_class_path"
                    echo "---------------- Output ------------------"
                    cat "$tmp_output"
                    echo "=========================================="
                    echo ""
                } >> "$LOG_JAVAP"
            fi
        fi

        rm -f "$tmp_output"

    done < "$list_file"

done < <(find "$CLASSLIST_DIR" -maxdepth 1 -type f | sort)

echo -e "\n=========================================="
echo " Process Finished (MODE: $MODE)."
echo "=========================================="