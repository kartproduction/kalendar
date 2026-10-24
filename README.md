# Kalendar by Kasign — app

Offline-first calendar app: 10 designs, portrait and landscape, PNG/PDF export, Nepali BS dates (2082–2088 BS), festivals, events, and customization. HTML/CSS/JS, packaged for Android with Capacitor. The main website is a separate project (`kalendar-website`) that links to this repo's Releases.

## Easiest: make the APK online (no computer, no Android Studio)
1. Create a GitHub repo and upload this whole folder (GitHub website > Add file > Upload files, or `git push`).
2. Open the **Actions** tab > **Build APK** > **Run workflow**.
3. After a few minutes, open **Releases**: the file `Kalendar-v1.0.N.apk` is there. Open that link on your Android phone, download, install.
This builds a debug-signed APK for testing and personal use. Each run uses a new debug key, so a later build may not install over an older one (uninstall the old one first). For updates people can install over the top, use the signed release below.

## First-time setup (local, optional)
```bash
npm install @capacitor/core @capacitor/android @capacitor/filesystem @capacitor/share jspdf
npm install -D @capacitor/cli
npx cap add android
npm run sync
```
Commit `package.json` and `package-lock.json`. Then open `android/` in Android Studio, or run `npm run apk:debug` (APK in `android/app/build/outputs/apk/debug/`). Test on a real phone.

## Your photos
Add `03.jpg` ... `10.jpg` to `www/assets/images/` (see the README there). They load automatically. Photos added inside the app are session-only.

## Customize
Open `www/index.html`:
- `DESIGNS`: names, colors, fonts, quotes, art type, collection.
- `BSD`: BS month lengths (2082–2088). 2083 matches published calendars; other years come from public tables and are not independently checked. Anchor: Baisakh 1, 2083 = 14 Apr 2026.
- `FEST`: festival list as [start, end, name] in AD dates.

## Web app (GitHub Pages)
Settings > Pages > Source: **GitHub Actions**. The `Deploy web app` workflow publishes `www/` at `https://kartproduction.github.io/kalendar/`. It works offline after the first visit and can be added to a home screen. Put this URL in the website's `config.js` (`webApp`) if your repo name differs.

## App icon (Android)
`assets/icon-only.png` is a placeholder. Replace it with your logo, then run `npx @capacitor/assets generate --android` after `npx cap add android`.

## Release the APK (GitHub Actions)
1. Make a keystore once and back it up: `keytool -genkey -v -keystore release.jks -alias kalendar -keyalg RSA -keysize 2048 -validity 10000`
2. In GitHub, Settings > Secrets, add: `KEYSTORE_BASE64` (`base64 -w0 release.jks`), `KEYSTORE_PASSWORD`, `KEY_PASSWORD`, `KEY_ALIAS`.
3. Tag and push: `git tag v1.0.0 && git push origin v1.0.0`. The signed APK is attached to the release; the website's download button picks it up.
Never commit the keystore or passwords. Use the same key for every update.

## Licenses
Choose a code license (add `LICENSE`). Document rights for your photos, fonts and quotes separately. jsPDF is MIT-licensed.

## Notes
- Capacitor/Gradle/Java requirements change; check the current Capacitor Android docs if a build fails.
- Saving on Android uses the Filesystem and Share plugins (share sheet). Test on a device.
