import json
import subprocess
import sys
import tempfile
from pathlib import Path

def extract_raw_hex(node):
    hex_str = ""
    if isinstance(node, dict):
        for k, v in node.items():
            if k.startswith("---"):
                continue
            hex_str += extract_raw_hex(v)
    elif isinstance(node, list):
        for item in node:
            hex_str += extract_raw_hex(item)
    elif isinstance(node, str):
        hex_str += node
    return hex_str

def validate_with_javap(json_path: str, orig_class_path: str):
    try:
        with open(json_path, "r", encoding="utf-8") as f:
            data = json.load(f)
    except Exception as e:
        print(f"❌ [JSON Read Error] {e}")
        return False

    raw_hex = extract_raw_hex(data)
    class_bytes = bytes.fromhex(raw_hex)

    with tempfile.NamedTemporaryFile(suffix=".class", delete=False) as tmp_file:
        temp_class = Path(tmp_file.name)
        temp_class.write_bytes(class_bytes)

    try:
        with open(orig_class_path, "rb") as f1, open(temp_class, "rb") as f2:
            b1 = f1.read()
            b2 = f2.read()

        print(f"Original size: {len(b1)}, Reconstructed size: {len(b2)}")

        mismatch_offset = None
        for i in range(min(len(b1), len(b2))):
            if b1[i] != b2[i]:
                mismatch_offset = i
                print(f"\n[!] 첫 번째 바이트 불일치 지점:")
                print(f" - Hex Offset : {hex(i)}")
                print(f" - Decimal    : {i} 바이트 위치")
                print(f" - Original   : 0x{b1[i]:02X}")
                print(f" - Reconstructed: 0x{b2[i]:02X}")
                break

        if mismatch_offset is not None:
            start = max(0, mismatch_offset - 8)
            end = min(len(b1), mismatch_offset + 16)
            print("\n[주변 바이트 비교 (Offset기준)]")
            print("Original   :", " ".join(f"{b:02X}" for b in b1[start:end]))
            print("Reconstruct:", " ".join(f"{b:02X}" for b in b2[start:end]))

        res = subprocess.run(
            ["javap", "-v", str(temp_class)],
            capture_output=True,
            text=True,
            check=True,
        )
        print("✅ Javap Parsing Verification PASSED")
        return True
    except subprocess.CalledProcessError as e:
        print("❌ Javap Parsing FAILED (Class bytecode is corrupted/shifted):")
        print(e.stderr)
        return False
    except Exception as e:
        print(f"❌ Verification Exception: {e}")
        return False
    finally:
        if temp_class.exists():
            temp_class.unlink()

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python validate_javap.py <path_to_json> <path_to_orig_class>")
        sys.exit(1)

    target_json = sys.argv[1]
    orig_class = sys.argv[2]

    if validate_with_javap(target_json, orig_class):
        sys.exit(0)
    else:
        sys.exit(1)