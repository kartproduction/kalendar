#!/usr/bin/env bash
# Prepares the Android project inside GitHub Actions (no local setup needed).
set -e
npm install --no-audit --no-fund
npm install --no-audit --no-fund @capacitor/core @capacitor/android @capacitor/filesystem @capacitor/share jspdf
npm install -D --no-audit --no-fund @capacitor/cli
[ -d android ] || npx cap add android
npm run copy:deps

# Stamp the page so the app shows which build it is (bottom of the page).
BUILD="build-${GITHUB_RUN_NUMBER:-local}-$(git rev-parse --short HEAD 2>/dev/null || echo na)"
sed -i "s/__BUILD__/$BUILD/g" www/index.html
[ -n "$GITHUB_ENV" ] && echo "BUILD=$BUILD" >> "$GITHUB_ENV"
echo "== Building $BUILD"
cat capacitor.config.json
grep -q '"url"' capacitor.config.json && echo "WARNING: server.url is set, so the app would load a website instead of the bundled page."

# Remove any old copy of the web files inside the Android project, then copy fresh ones.
rm -rf android/app/src/main/assets/public
npx cap sync android

grep -q 'id="live"' www/index.html || { echo "ERROR: www/index.html is not the new page"; exit 1; }
grep -q "$BUILD" android/app/src/main/assets/public/index.html || { echo "ERROR: the new page was not copied into the Android project"; exit 1; }
echo "== OK: Android project contains $BUILD"
