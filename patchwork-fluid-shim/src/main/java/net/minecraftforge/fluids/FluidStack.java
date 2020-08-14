package net.minecraftforge.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.PacketByteBuf;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FluidStack {
	private static final Logger LOGGER = LogManager.getLogger();

	public static final FluidStack EMPTY = new FluidStack(Fluids.EMPTY, 0);

	private boolean isEmpty;
	private int amount;
	private CompoundTag tag;

	public FluidStack(Fluid fluid, int amount)
	{
		this.amount = amount;

		updateEmpty();
	}

	public FluidStack(Fluid fluid, int amount, CompoundTag nbt)
	{
		this(fluid, amount);

		if (nbt != null)
		{
			tag = nbt.copy();
		}
	}

	public FluidStack(FluidStack stack, int amount)
	{
		this(stack.getFluid(), amount, stack.tag);
	}

	/**
	 * This provides a safe method for retrieving a FluidStack - if the Fluid is invalid, the stack
	 * will return as null.
	 */
	public static FluidStack loadFluidStackFromNBT(CompoundTag nbt)
	{
		return EMPTY;
	}

	public CompoundTag writeToNBT(CompoundTag nbt)
	{
		return nbt;
	}

	public void writeToPacket(PacketByteBuf buf)
	{

	}

	public static FluidStack readFromPacket(PacketByteBuf buf)
	{
		return EMPTY;
	}

	public final Fluid getFluid()
	{
		return Fluids.EMPTY;
	}

	public final Fluid getRawFluid()
	{
		return Fluids.EMPTY;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	protected void updateEmpty() {
		isEmpty = true;
	}

	public int getAmount()
	{
		return isEmpty ? 0 : amount ;
	}

	public void setAmount(int amount)
	{

	}

	public void grow(int amount) {
		setAmount(this.amount + amount);
	}

	public void shrink(int amount) {
		setAmount(this.amount - amount);
	}

	public boolean hasTag()
	{
		return tag != null;
	}

	public CompoundTag getTag()
	{
		return tag;
	}

	public void setTag(CompoundTag tag)
	{

	}

	public CompoundTag getOrCreateTag()
	{
		if (tag == null)
			setTag(new CompoundTag());
		return tag;
	}

	public CompoundTag getChildTag(String childName)
	{
		if (tag == null)
			return null;
		return tag.getCompound(childName);
	}

	public CompoundTag getOrCreateChildTag(String childName)
	{
		getOrCreateTag();
		CompoundTag child = tag.getCompound(childName);
		return child;
	}

	public void removeChildTag(String childName)
	{
		if (tag != null)
			tag.remove(childName);
	}

	public Text getDisplayName()
	{
		return new LiteralText("Empty");
	}

	public String getTranslationKey()
	{
		return "empty";
	}

	/**
	 * @return A copy of this FluidStack
	 */
	public FluidStack copy()
	{
		return new FluidStack(getFluid(), amount, tag);
	}

	/**
	 * Determines if the FluidIDs and NBT Tags are equal. This does not check amounts.
	 *
	 * @param other
	 *            The FluidStack for comparison
	 * @return true if the Fluids (IDs and NBT Tags) are the same
	 */
	public boolean isFluidEqual(@Nonnull FluidStack other)
	{
		return getFluid() == other.getFluid() && isFluidStackTagEqual(other);
	}

	private boolean isFluidStackTagEqual(FluidStack other)
	{
		return tag == null ? other.tag == null : other.tag != null && tag.equals(other.tag);
	}

	/**
	 * Determines if the NBT Tags are equal. Useful if the FluidIDs are known to be equal.
	 */
	public static boolean areFluidStackTagsEqual(@Nonnull FluidStack stack1, @Nonnull FluidStack stack2)
	{
		return stack1.isFluidStackTagEqual(stack2);
	}

	/**
	 * Determines if the Fluids are equal and this stack is larger.
	 *
	 * @param other
	 * @return true if this FluidStack contains the other FluidStack (same fluid and >= amount)
	 */
	public boolean containsFluid(@Nonnull FluidStack other)
	{
		return isFluidEqual(other) && amount >= other.amount;
	}

	/**
	 * Determines if the FluidIDs, Amounts, and NBT Tags are all equal.
	 *
	 * @param other
	 *            - the FluidStack for comparison
	 * @return true if the two FluidStacks are exactly the same
	 */
	public boolean isFluidStackIdentical(FluidStack other)
	{
		return isFluidEqual(other) && amount == other.amount;
	}

	/**
	 * Determines if the FluidIDs and NBT Tags are equal compared to a registered container
	 * ItemStack. This does not check amounts.
	 *
	 * @param other
	 *            The ItemStack for comparison
	 * @return true if the Fluids (IDs and NBT Tags) are the same
	 */
	public boolean isFluidEqual(@Nonnull ItemStack other)
	{
		return false;
	}

	@Override
	public final int hashCode()
	{
		int code = 1;
		code = 31*code + getFluid().hashCode();
		code = 31*code + amount;
		if (tag != null)
			code = 31*code + tag.hashCode();
		return code;
	}

	/**
	 * Default equality comparison for a FluidStack. Same functionality as isFluidEqual().
	 *
	 * This is included for use in data structures.
	 */
	@Override
	public final boolean equals(Object o)
	{
		if (!(o instanceof FluidStack))
		{
			return false;
		}
		return isFluidEqual((FluidStack) o);
	}
}
