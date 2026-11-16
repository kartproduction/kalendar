# Kalendar by Kasign — app

Offline-first calendar app with 10 designs, portrait and landscape layouts, PNG/PDF export, Nepali (BS) dates, festivals, personal events (including yearly birthdays and anniversaries), a coming-up list, event backup and restore, an AD ↔ BS date converter, event colors, a full-year view, a Nepali (BS) month view, reminder notifications (Android app), update and backup notices, and customization. One web app, also packaged as an Android app with Capacitor.

- **Web app:** https://kartproduction.github.io/kalendar/
- **Android app:** download from https://kartproduction.github.io/kalendarbykasign/download.html (files are published in [Releases](https://github.com/kartproduction/kalendar/releases))
- **Website source:** https://github.com/kartproduction/kalendarbykasign

> **View-only source.** This repository is public so you can see how the app was made. The code, designs, photos and branding are **not** free to copy, host or reuse. See [LICENSE](LICENSE).

## Folder structure (keep files in these places)

```
kalendar/
├── www/                      the app
│   ├── index.html            the whole app page  (replace this to update the app)
│   ├── assets/images/        your photos: 03.jpg ... 10.jpg
│   ├── icons/, sw.js, manifest.webmanifest
├── .github/workflows/
│   ├── build-apk.yml         builds the signed Android APK (Actions > Build APK)
│   └── pages.yml             publishes www/ as the web app
├── scripts/                  build helper scripts
├── capacitor.config.json
├── package.json
├── LICENSE
└── README.md
```

Files placed outside these folders (for example an `index.html` in the repo root) are ignored by the build.

## Update the app

1. Replace `www/index.html` (open the `www` folder first, then **Add file > Upload files**).
2. Add photos to `www/assets/images/` as `03.jpg` ... `10.jpg` (lowercase `.jpg`).
3. Open **Actions > Build APK > Run workflow**. The new APK appears in Releases, and the web app updates by itself.
4. Check the line "App build: build-N-xxxxxxx" at the bottom of the app page to confirm which build you are running.

## Data you can edit in `www/index.html`

- `DESIGNS`: names, colors, fonts, quotes, art type and collection for each design.
- `BSD`: BS month lengths for 2082–2088. 2083 matches published calendars; other years come from public tables and are not independently verified. Anchor: Baisakh 1, 2083 = 14 Apr 2026.
- `FEST`: festival list as `[start, end, name]` in AD dates.

## App icon

The build has the Kalendar icon built in, so the app never keeps the default icon. In the build log, the step "Put the app icon into the Android project" prints the icon it used, and a later step prints "ICON OK" when the finished APK contains it.

To use your own logo, create a top-level `assets` folder and add `icon-only.png` (1024×1024). Optional: `icon-foreground.png` (your logo on a transparent background, kept inside the middle 60% so Android's round mask does not cut it) and `icon-background.png` (a plain color or gradient). Without a foreground file, the build makes one from `icon-only.png`. Also replace `www/icons/icon-192.png` and `icon-512.png` for the web app.

## Festival data

Festival and public-holiday entries (`FEST` in `www/index.html`) follow the Ministry of Home Affairs public holiday notice for 2083 BS, plus well-known festivals. Eid al-Fitr, Eid al-Adha, Bhoto Jatra, Saruwa Pawani, Muhammad Jayanti and Guru Nanak Jayanti are announced later, so they are not listed. When the notice for 2084 BS is published, add its dates to `FEST`. Purnima, Aunsi and Ekadashi are calculated, so they can be off by a day. Nepali BS dates for 2083 match published calendars. The other years come from public tables and are not independently checked.

## Notices in the Android app

- **New version:** the app asks GitHub for the newest release number when it opens, at most every six hours, and shows an "Update" notice if a newer APK exists. It needs the "App build" stamp that **Build APK** adds.
- **Backup nudge:** every 30 days, if you have events and have not exported a backup, the app suggests it.

## Signed release (updates that install over the old app)

1. Create a keystore once and back it up: `keytool -genkey -v -keystore release.jks -alias kalendar -keyalg RSA -keysize 2048 -validity 10000`
2. In GitHub **Settings > Secrets and variables > Actions**, add `KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_PASSWORD` and `KEY_ALIAS`.
3. Run **Build APK**. Never commit the keystore or passwords, and use the same key for every update.

## Contact

GitHub: [@kartproduction](https://github.com/kartproduction)
