# unhinted

a small fabric client mod. it turns off truetype hinting for resource pack fonts.

## why

hinting is code inside a font file that the font library runs when it draws a letter. a bug in that step (minecraft2shell, freetype #1419) let a crafted font run code on your pc. with hinting off, that code never runs.

## what it does

adds the no-hinting flag to every glyph load for truetype fonts. small text may look a little softer.

## is it the real fix?

no. mojang's fix is the real one. minecraft 26.3 now ships a patched freetype (2.15.0). restart your launcher fully to get it. this mod is for older versions that have no fix: 1.20.5 to 1.21.11 and 26.1 to 26.2.

## versions

fabric loader 0.15.10 or newer, java 21 or newer, minecraft 1.20.5 to 1.21.11, 26.1, 26.1.1, 26.1.2 and 26.2 (26.3 also works, where you don't need it). if the hook isn't found on a version, the game stops with an error on startup instead of running unprotected. these are the same versions listed on modrinth. client only. no fabric api needed.

## what was tested

- the hook point (one FT_Load_Glyph call, same flags) exists in the font class of every release from 1.20.5 to 1.21.11 and 26.1 to 26.3, checked by reading the real client jars.
- the flag change was checked with a headless freetype run (the glyph output changes when hinting is off).
- not tested: the public exploit. not tested in a running game on every version.

## build

`sh build.sh` (jdk 21 or newer, curl). mit license.

## also

only trust resource packs from servers you trust. you can set server resource packs to disabled in the server's edit screen.
