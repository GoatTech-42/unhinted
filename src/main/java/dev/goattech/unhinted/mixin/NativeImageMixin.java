package dev.goattech.unhinted.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * NativeImage.copyFromFont loads the glyph a second time (to draw it).
 * It must use the same flags as the font provider, so no-hinting goes here too.
 */
@Mixin(targets = {
        "com.mojang.blaze3d.platform.NativeImage",
        "net.minecraft.class_1011"
}, remap = false)
public abstract class NativeImageMixin {
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
