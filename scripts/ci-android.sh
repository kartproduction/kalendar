#!/usr/bin/env bash
# Prepares the Android project inside GitHub Actions (no local setup needed).
set -e
npm install --no-audit --no-fund
npm install --no-audit --no-fund @capacitor/core @capacitor/android @capacitor/filesystem @capacitor/share jspdf
npm install -D --no-audit --no-fund @capacitor/cli
[ -d android ] || npx cap add android
npm run copy:deps
npx cap sync android
