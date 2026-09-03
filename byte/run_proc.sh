#!/bin/bash

CLASSLIST_PART=$1
OUTBASE=/data2/minha/data/output
LOGBASE=/data2/minha/data/logs
FINISHLOG=$LOGBASE/finish.log
NPROC=1

name=$(basename "$CLASSLIST_PART")

if [[ $name == anon_* ]]; then
  OUTDIR=$OUTBASE/anon/$name
elif [[ $name == non_anon_za* ]]; then
  OUTDIR=$OUTBASE/non_anon_za/$name
elif [[ $name == non_anon_zb* ]]; then
  OUTDIR=$OUTBASE/non_anon_zb/$name
elif [[ $name == non_anon_zc* ]]; then
  OUTDIR=$OUTBASE/non_anon_zc/$name
elif [[ $name == non_anon_zd* ]]; then
  OUTDIR=$OUTBASE/non_anon_zd/$name
elif [[ $name == non_anon_* ]]; then
  OUTDIR=$OUTBASE/non_anon/$name
else
  exit 0
fi

LOGFILE=$LOGBASE/${name}.err.log
mkdir -p "$OUTDIR" "$LOGBASE"
touch "$FINISHLOG"

# 이미 완료된 리스트라면 건너뛰기
if grep -Fxq "$name" "$FINISHLOG"; then
  exit 0
fi

echo "▶ $name 시작"
start_time=$(date +%s)

# Gradle 실행 (--console=plain 추가로 로그 터미널 제어문자 방지)
./gradlew run --build-cache --daemon --max-workers=1 --console=plain \
  --args="-m $CLASSLIST_PART $OUTDIR" >/dev/null 2>>"$LOGFILE"

total=$(wc -l < "$CLASSLIST_PART")

# JSON 파일 개수 카운트
count=$(ls -1 "$OUTDIR"/*.json 2>/dev/null | wc -l)

end_time=$(date +%s)
elapsed=$((end_time - start_time))

# 모든 클래스가 성공적으로 JSON으로 변환되었으면 완료 기록
if [ "$count" -eq "$total" ]; then
  echo "$name" >> "$FINISHLOG"
fi

# awk를 활용해 bc 미설치 환경에서도 안전하게 평균 시간 계산
avg_per_proc=$(awk "BEGIN {printf \"%.2f\", $elapsed / $NPROC}")
echo "✅ $name (${count}/${total}) ${avg_per_proc}s"

# 에러 로그 파일이 비어있으면 깔끔하게 삭제
if [ ! -s "$LOGFILE" ]; then
  rm -f "$LOGFILE"
fi
