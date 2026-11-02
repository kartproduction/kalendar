# Kalendar by Kasign

Beautiful calendars with Nepali dates. Pick a design, add your own photo or quote, and export it as an image or PDF. It is free, works offline, and needs no account.

- **Website:** https://kartproduction.github.io/kalendarbykasign/
- **Web app:** https://kartproduction.github.io/kalendar/
- **Android app:** download the latest APK from [Releases](https://github.com/kartproduction/kalendar/releases)

> **View-only source.** This repository is public so you can see how the app was made and learn from it. The code is not free to copy, host, or reuse. See [License](#license).

---

## What it does

- **10 designs in 5 collections:** Paper, Himalaya, Words, Patro, and Bloom. Mountain and hill scenes are drawn in code, so no photos are needed for them.
- **Nepali (BS) dates:** switch between AD, AD + BS, or BS with one tap. Covers 2082–2088 BS, with Nepali weekday names and Nepali digits as options.
- **English / नेपाली interface:** one button switches the page's buttons, labels and notes between English and Nepali, and remembers your choice.
- **Export:** save a calendar page as a PNG or as a PDF (A4 or Letter), in portrait (9:16) or landscape (16:9). A **full-year PDF** makes a cover page plus all 12 months in one file, and **Share image** sends the picture straight to WhatsApp, Viber or any other app.
- **Make it yours:** add your own photos, a custom quote and author, an accent color, one of five fonts, and choose whether the week starts on Sunday or Monday. **Save my design** keeps your choices so next month takes one tap (photos are not saved).
- **My Calendar:** add your own events and notes by date, with an icon for birthday, anniversary, exam, party, work or trip. Birthdays and anniversaries can show the age or years together if you type the year (for example, "Ram 1996"). Search finds any event or festival across all months.
- **Phone reminders and calendar export:** export events and festivals as an `.ics` file for Google or Apple Calendar ("Every year" events repeat yearly). In the Android app, your events also set an 8:00 am reminder on the day.
- **Festivals and holidays:** the list for 2083 BS (April 2026 to April 2027) appears under the month and on the dates, including Dashain, the five days of Tihar, Teej, Janai Purnima, Maha Shivaratri, Holi, Ghode Jatra and the Lhosar festivals.
- **Moon and tithi:** Purnima and Aunsi are marked on the grid, Ekadashi is listed under the month, and tapping a date shows its tithi. These are calculated, so they are approximate and can differ by a day from a printed patro.
- **Dark mode:** a button in the top bar switches the page between light and dark.
- **Favorites:** mark designs you like.
- **Offline-first:** after the first visit the web app works without a connection, and it can be added to a phone home screen.

Events, favorites, saved designs, and customization are saved on your own device. Photos you add last only for the current session.

## How it was made

| Part | What it uses |
| --- | --- |
| App | One HTML file with its own CSS and JavaScript (`www/index.html`), drawn on an HTML canvas |
| Reminders | [@capacitor/local-notifications](https://capacitorjs.com/docs/apis/local-notifications) in the Android app; the web page skips this step |
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
├── capacitor.config.json     # Android app settings (includes the notification plugin)
├── package.json
└── LICENSE
```

### Where the festival dates come from

The 2083 BS list was put together from the Government of Nepal public holiday notice as reproduced by public sources (Pradhan Law, College NP, ExamSanjal), checked against nepcalendar.com and Calendarific. Sources disagree on a few lunar days, so these are not official: Bhai Tika is listed on 11 November 2026 (nepcalendar.com and Nepal Hiking Team), while Calendarific shows 10 November. Check the government gazette before relying on a date, and edit the `FEST` list in `www/index.html` to correct anything. Dates for 2084 BS onward are not in the app yet.

### How the Nepali dates work

Month lengths for each BS year live in a lookup table. The app counts the days between a given date and a known anchor (Baisakh 1, 2083 = 14 April 2026) and walks forward or backward through that table. The year 2083 matches published calendars. The other years come from public converter tables and have not been independently checked.

## Build and release

These notes are for the maintainer.

- **Reminders (one-time setup):** run `npm install @capacitor/local-notifications` using the same Capacitor major version as the rest of the app, commit the updated `package.json` and `package-lock.json`, then run `npm run sync`. Check that the Android manifest allows notifications on Android 13 and newer (`POST_NOTIFICATIONS`).
- **Fonts and offline:** keep the files in `www/fonts/` listed in `www/sw.js` so the Fraunces, Playfair, Archivo and Tiro Devanagari fonts work offline.

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
