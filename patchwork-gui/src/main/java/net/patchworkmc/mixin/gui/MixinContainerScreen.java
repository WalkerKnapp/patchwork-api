package net.patchworkmc.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.container.Slot;

@Mixin(ContainerScreen.class)
public class MixinContainerScreen {
	@Shadow
	protected int containerWidth;

	@Shadow
	protected int containerHeight;

	@Shadow
	protected int x;

	@Shadow
	protected int y;

	@Shadow
	protected Slot focusedSlot;

	protected int slotColor = -2130706433;

	public Slot getSlotUnderMouse() {
		return this.focusedSlot;
	}

	public int getGuiLeft() {
		return x;
	}
	public int getGuiTop() {
		return y;
	}

	public int getXSize() {
		return containerWidth;
	}
	public int getYSize() {
		return containerHeight;
	}

	public int getSlotColor(int index) {
		return slotColor;
	}
}
