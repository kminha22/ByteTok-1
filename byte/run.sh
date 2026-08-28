#!/bin/bash
CLASSLIST_DIR=/data2/minha/data/classlist
NPROC=10   # 동시에 실행할 프로세스 개수 제한

for part in "$CLASSLIST_DIR"/*; do
  ./run_proc.sh "$part" &

  # 동시에 NPROC 개까지만 실행
  while (( $(jobs -r | wc -l) >= NPROC )); do
    wait -n
  done
done

wait   # 모든 프로세스 종료 대기
