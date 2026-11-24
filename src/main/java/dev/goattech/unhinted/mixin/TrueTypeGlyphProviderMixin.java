package dev.goattech.unhinted.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * Adds FT_LOAD_NO_HINTING (0x2) to every FT_Load_Glyph call made for TrueType
 * resource pack fonts, so the font's own hinting bytecode is never run.
 * Two target names: mojang names (26.x) and fabric intermediary (1.20.5 to 1.21.x).
 */
@Mixin(targets = {
        "com.mojang.blaze3d.font.TrueTypeGlyphProvider",
        "net.minecraft.class_395"
}, remap = false)
public abstract class TrueTypeGlyphProviderMixin {
    @ModifyArg(
            method = "/.*/",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/util/freetype/FreeType;FT_Load_Glyph(Lorg/lwjgl/util/freetype/FT_Face;II)I",
                    remap = false
            ),
            index = 2,
            remap = false
    )
    private int unhinted$noHinting(int loadFlags) {
        return loadFlags | 0x2;
    }
}
