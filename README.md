# Kalendar by Kasign

Beautiful calendars with Nepali dates. Pick a design, add your own photo or quote, and export it as an image or PDF. It is free, works offline, and needs no account.

- **Website:** https://kartproduction.github.io/kalendarbykasign/
- **Web app:** https://kartproduction.github.io/kalendar/
- **Android app:** download the latest APK from [Releases](https://github.com/kartproduction/kalendar/releases)

> **View-only source.** This repository is public so you can see how the app was made and learn from it. The code is not free to copy, host, or reuse. See [License](#license).

---

## What it does

- **10 designs in 5 collections:** Paper, Himalaya, Words, Patro, and Bloom. Mountain and hill scenes are drawn in code, so no photos are needed for them.
- **Nepali (BS) dates:** switch between AD, AD + BS, or BS emphasis. Covers 2082–2088 BS, with Nepali weekday names as an option.
- **Export:** save a calendar page as a PNG or as a PDF (A4 or Letter), in portrait (9:16) or landscape (16:9).
- **Make it yours:** add your own photos, a custom quote and author, an accent color, a font, and choose whether the week starts on Sunday or Monday.
- **My Calendar:** add your own events and notes by date.
- **Festivals:** a short list of 2026 festivals (Nepali New Year, Ghatasthapana, Dashain, Tihar) appears under the month.
- **Favorites:** mark designs you like.
- **Offline-first:** after the first visit the web app works without a connection, and it can be added to a phone home screen.

Events, favorites, and customization are saved on your own device. Photos you add last only for the current session.

## How it was made

| Part | What it uses |
| --- | --- |
| App | One HTML file with its own CSS and JavaScript (`www/index.html`), drawn on an HTML canvas |
| PDF export | [jsPDF](https://github.com/parallax/jsPDF) |
| Offline and install | A service worker (`www/sw.js`) and a web app manifest |
| Android app | [Capacitor](https://capacitorjs.com/), plus a small custom Java bridge that saves exports to the phone's Pictures and Downloads folders (Android 10 and newer) |
| Fonts | Fraunces, Playfair Display, Bodoni Moda, Archivo, and Tiro Devanagari Hindi, bundled so the designs look the same offline |
| Web hosting | GitHub Pages, deployed automatically by GitHub Actions |
| APK builds | GitHub Actions builds and signs the APK, then publishes it to Releases |

### Project structure

```
kalendar/
├── www/                      # the app itself
│   ├── index.html            # designs, date logic, export, UI
│   ├── sw.js                 # offline cache
│   ├── manifest.webmanifest  # install-to-home-screen settings
│   ├── fonts/                # bundled fonts
│   ├── icons/                # app icons
│   └── assets/images/        # photos for the designs
├── scripts/                  # build helper scripts
├── .github/workflows/        # web deploy, APK build, release
├── capacitor.config.json     # Android app settings
├── package.json
└── LICENSE
```

### How the Nepali dates work

Month lengths for each BS year live in a lookup table. The app counts the days between a given date and a known anchor (Baisakh 1, 2083 = 14 April 2026) and walks forward or backward through that table. The year 2083 matches published calendars. The other years come from public converter tables and have not been independently checked.

## Build and release

These notes are for the maintainer.

- **Web app:** every push to `main` runs the *Deploy web app* workflow and publishes the `www/` folder to GitHub Pages.
- **Test APK:** run *Build APK* from the Actions tab. It builds a signed APK, numbers it `1.0.N`, and attaches it to a new release.
- **Tagged release:** pushing a tag that starts with `v` (for example `v1.0.0`) runs *Android release*.
- **Local build:** `npm run sync` copies jsPDF into `www/js/` and syncs the Android project. `npm run apk:debug` builds a debug APK.
- **Signing key:** the keystore and its passwords are stored as GitHub Secrets and are never committed. Use the same key for every update so people can install new versions over old ones.

## Contributing

Bug reports and suggestions are welcome in [Issues](https://github.com/kartproduction/kalendar/issues). Pull requests can be opened for review, but the owner decides what gets merged. By submitting a contribution, you agree it may be used in this project under the same license.

## License

Copyright (c) 2026 kartproduction. All rights reserved.

The source code is visible for **viewing and learning only**. You may not copy, host, redistribute, modify for your own use, or build derivative products from it without written permission. See [LICENSE](LICENSE) for the full terms.

Third-party components (jsPDF, Capacitor, and the bundled fonts) keep their own licenses. Photos, fonts, and quotes used in the designs have their own rights and are not covered by any permission to reuse this code.

## Contact

Made with care by **Kasign** · Kart Production
GitHub: [@kartproduction](https://github.com/kartproduction)
