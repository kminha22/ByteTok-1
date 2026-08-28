#!/bin/bash

CLASSLIST_PART=$1
OUTBASE=/data2/minha/data/output
LOGBASE=/data2/minha/data/logs
FINISHLOG=$LOGBASE/finish.log
NPROC=10

name=$(basename "$CLASSLIST_PART")

if [[ $name == non_anon_zc* ]]; then
  OUTDIR=$OUTBASE/non_anon_zc/$name
elif [[ $name == non_anon_zd* ]]; then
  OUTDIR=$OUTBASE/non_anon_zd/$name
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

./gradlew run --build-cache --daemon --max-workers=6 \
  --args="-j $CLASSLIST_PART $OUTDIR" >/dev/null 2>>"$LOGFILE"

total=$(wc -l < "$CLASSLIST_PART")
count=$(ls -1 "$OUTDIR"/*.json 2>/dev/null | wc -l)
end_time=$(date +%s)
elapsed=$((end_time - start_time))

if [ "$count" -eq "$total" ]; then
  echo "$name" >> "$FINISHLOG"
fi

avg_per_proc=$(echo "$elapsed / $NPROC" | bc -l)
echo "✅ $name (${count}/${total}) $(printf "%.2f" "$avg_per_proc")s"


if [ ! -s "$LOGFILE" ]; then
  rm -f "$LOGFILE"
fi
