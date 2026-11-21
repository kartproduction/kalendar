# Testing Kalendar on real phones

I could only test in a computer browser, so please test on real phones. Use **at least one older phone (Android 9 or 10) and one newer phone (Android 13 or newer)**. Ask friends or family to help.

## 1. Start with the built-in self-check
Open the app, scroll to **About, help and feedback**, tap **Run self-check**, then **Copy results** and send them to yourself or to me. It reports the Android version, whether saving, sharing, notifications and the widget are available, whether the fonts loaded, and whether a PNG can be made.

Also tap **Test saving a file**. It should say where it saved a small test picture (Pictures/Kalendar on Android 10 or newer, or the share sheet on older phones).

## 2. Checklist (tick each one)
| Test | Older phone | Newer phone |
|---|---|---|
| App installs and opens, correct icon and name | | |
| Today's date is highlighted, BS dates correct (compare with a paper patro) | | |
| Switch to Nepali and back (button at the top) | | |
| Fonts look right (headings are not plain system fonts) | | |
| Save a PNG in portrait and landscape, open it in Gallery | | |
| Save a PDF (A4), open it | | |
| Add an event, restart the app, the event is still there | | |
| Turn on reminders, add an event for tomorrow, set the time a few minutes ahead, lock the phone | | |
| Export backup, then Import backup | | |
| Year view and BS month view open and close, back button works | | |
| Long-press the home screen, add the Kalendar widget, it shows today's BS date | | |
| Turn the phone's text size up to the largest, nothing is cut off | | |
| Turn on TalkBack, move through the calendar (see ACCESSIBILITY.md) | | |
| Turn on airplane mode, the app still works | | |

## 3. What to write down for each problem
Phone model, Android version, what you tapped, what you expected, what happened, and a screenshot. The self-check results help a lot.

## Notes
- Saving files straight to the phone needs Android 10 or newer. On older phones the app should open the share sheet instead. If it does not, that is a bug to report.
- Reminders can be a few minutes late on some phones because of battery saving. If they never come, check Settings > Apps > Kalendar > Notifications and Battery.
- The home-screen widget updates about every 30 minutes and at midnight. It shows dates the app has sent it, so open the app once after installing or when you change the language.
