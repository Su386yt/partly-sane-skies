//
// Written by Su386.
// See COPYING for copyright and license notices.
//


package me.partlysanestudios.partlysaneskies.mixin.minecraft;

import net.minecraft.client.gui.inventory.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiContainer.class)
public interface MixinGuiContainer {
    @Accessor("xSize")
    int partlysaneskies$getXSize();

    @Accessor("ySize")
    int partlysaneskies$getYSize();
}
