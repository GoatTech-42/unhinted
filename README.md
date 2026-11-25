patches the Minecraft2Shell exploit on older unpatched versions

## why

hinting is a small program stored inside a font file. the font library runs it when it draws a letter. a bug in that step (the minecraft2shell bug, freetype issue 1419) let a crafted font in a resource pack run code on your pc. with hinting off, that code never runs.

## is this the real fix?

no. mojang's fix is the real fix. minecraft 26.3 now ships a patched freetype, so on 26.3 you only need to fully restart your launcher. you don't need this mod there.

this mod is a stopgap for older versions that don't have a fix: 1.20.5 to 26.2.

## what it does

it adds the no-hinting flag to every glyph load for truetype fonts. small text can look a little softer.

## versions

minecraft 1.20.5 to 1.21.11 and 26.1 to 26.2. 26.3 loads it too but doesn't need it. fabric loader 0.15.10 or newer, java 21 or newer. client only. no fabric api needed.

if the font code isn't found on a version, the game stops with an error at startup. it won't run unprotected. tested: the mod loaded in a real headless fabric client with a ttf font pack on every listed version, and the game reached the title screen with no crash.

## also

only accept resource packs from servers you trust. you can set server resource packs to disabled in a server's edit screen.

this mod was written with the help of ai.
