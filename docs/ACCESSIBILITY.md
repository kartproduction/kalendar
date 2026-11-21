# Accessibility

## What was improved in this version
- Text and button colors now meet the WCAG AA contrast ratio (4.5:1) in light and dark mode, checked automatically.
- Every button and input has a name for screen readers; the calendar days are read as full sentences ("Tuesday, 17 November 2026. Mangsir 1, 2083 BS. Festival: ..."), and today is marked.
- Year view and BS month view are announced as dialogs, move focus to their Close button when opened, and return focus when closed.
- Messages (saved, errors, reminders) are announced politely.
- A visible focus outline for keyboard and switch users.
- Buttons are at least 44 pixels high, and the calendar still fits without sideways scrolling at 200% zoom (about 195 pixels wide).
- Animations are switched off for people who use "reduce motion".

## Still needs a human check
1. **TalkBack**: Settings > Accessibility > TalkBack. Swipe through the page. Does each control make sense? Do the day labels read well? Is anything skipped or repeated?
2. **Largest text size**: Settings > Display > Font size and style. Check the calendar header, the buttons and the forms.
3. **Nepali screen reading**: switch the app to Nepali and listen to TalkBack. Some phones read Devanagari poorly without the Nepali voice installed.
4. **Color**: the design artwork (photos behind text) can reduce contrast. Check each design with your real photo and tell me if dates are hard to read.
