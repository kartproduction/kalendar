#!/usr/bin/env bash
# Adds the home-screen widget to the generated Android project.
# Run it after `npx cap add android` (the android/ folder is created fresh on every build).
set -e
A=android/app/src/main
D=$A/java/com/kasign/kalendar
[ -d "$A" ] || { echo "ERROR: android project not found (run cap add android first)"; exit 1; }
mkdir -p "$D"

# 1. Widget data (BS month lengths + festivals) generated from www/index.html
node scripts/gen-widget-data.js

# 2. Widget code and resources
cp widget/java/*.java "$D/"
cp -r widget/res/. "$A/res/"

# 3. Register the widget in the manifest (once)
if ! grep -q "KalendarWidgetProvider" "$A/AndroidManifest.xml"; then
  python3 - <<'PY'
p = "android/app/src/main/AndroidManifest.xml"
s = open(p, encoding="utf-8").read()
r = open("widget/manifest-receiver.xml", encoding="utf-8").read()
assert "</application>" in s, "AndroidManifest.xml has no </application>"
open(p, "w", encoding="utf-8").write(s.replace("</application>", r + "\n</application>", 1))
PY
fi
grep -q "KalendarWidgetProvider" "$A/AndroidManifest.xml" || { echo "ERROR: widget not registered"; exit 1; }
echo "== Widget added"
