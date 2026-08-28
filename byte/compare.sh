#!/bin/bash

CLASSLIST=/data2/minha/data/classlist/non_anon_cn
OUTDIR=/data2/minha/data/output/non_anon_0/non_anon_cn

# 클래스패스에서 basename만 추출 (확장자 .class 제거)
expected=$(sed 's#.*/##' "$CLASSLIST" | sed 's/\.class$//')

# 결과 디렉토리에서 basename만 추출 (확장자 .json 제거)
produced=$(ls "$OUTDIR"/*.json 2>/dev/null | xargs -n1 basename | sed 's/\.json$//')

# 집합 비교
echo "=== 누락된 클래스 ==="
comm -23 <(echo "$expected" | sort) <(echo "$produced" | sort)

echo "=== 추가된 결과 ==="
comm -13 <(echo "$expected" | sort) <(echo "$produced" | sort)
