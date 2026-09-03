import json
import sys

def validate_bytecode_json(json_path: str):
    try:
        with open(json_path, "r", encoding="utf-8") as f:
            data = json.load(f)
    except Exception as e:
        print(f"❌ [JSON Read Error] {e}")
        return False

    errors = []

    # 1. Constant Pool 개수 및 UTF8 길이 검증
    cp_count = int(data.get("Constant Pool Count", "0"), 16)
    cp_list = data.get("Constant Pool", [])

    expected_cp_entries = cp_count - 1
    actual_cp_entries = 0

    for idx, entry in enumerate(cp_list):
        tag = entry.get("tag")
        actual_cp_entries += 2 if tag in ["05", "06"] else 1

        if tag == "01":
            declared_len = int(entry.get("length", "0"), 16)
            hex_bytes = entry.get("bytes", "")
            actual_len = len(hex_bytes) // 2
            if declared_len != actual_len:
                errors.append(
                    f"[CP #{idx+1} UTF8 Length Mismatch] Declared: {declared_len}, Actual bytes: {actual_len}"
                )

    if expected_cp_entries > 0 and actual_cp_entries != expected_cp_entries:
        errors.append(
            f"[CP Count Mismatch] Declared Count-1: {expected_cp_entries}, Actual entries sum: {actual_cp_entries}"
        )

    # 2. Field / Method 개수 검증
    fields_count = int(data.get("Fields Count", "0"), 16)
    if len(data.get("Fields", [])) != fields_count:
        errors.append(
            f"[Fields Count Mismatch] Declared: {fields_count}, Actual: {len(data.get('Fields', []))}"
        )

    methods_count = int(data.get("Methods Count", "0"), 16)
    if len(data.get("Methods", [])) != methods_count:
        errors.append(
            f"[Methods Count Mismatch] Declared: {methods_count}, Actual: {len(data.get('Methods', []))}"
        )

    # 3. Method 내부 Code Length 검증
    for m_idx, method in enumerate(data.get("Methods", [])):
        for attr in method.get("Attribute Information", []):
            if attr.get("--- Type") == "Code":
                code_len = int(attr.get("codeLength", "0"), 16)
                code_hex_total = 0
                for inst in attr.get("code", []):
                    for k, v in inst.items():
                        if not k.startswith("---") and isinstance(v, str):
                            code_hex_total += len(v) // 2

                if code_len != code_hex_total:
                    errors.append(
                        f"[Method #{m_idx+1} Code Length Mismatch] Declared: {code_len}, Actual inst bytes: {code_hex_total}"
                    )

    if errors:
        print(f"❌ Structural Validation FAILED for {json_path}:")
        for err in errors:
            print(f"  - {err}")
        return False
    else:
        print(f"✅ Structural validation PASSED for {json_path}")
        return True

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python validate_struct.py <path_to_json>")
        sys.exit(1)

    target_json = sys.argv[1]
    if validate_bytecode_json(target_json):
        sys.exit(0)
    else:
        sys.exit(1)