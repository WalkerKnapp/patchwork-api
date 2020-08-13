package net.minecraftforge.fluids.capability.templates;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.function.Predicate;

public class FluidTank {

	protected FluidStack fluid = FluidStack.EMPTY;

	public FluidTank(int capacity)
	{

	}

	public FluidTank(int capacity, Predicate<FluidStack> validator)
	{

	}

	public FluidTank setCapacity(int capacity)
	{
		return this;
	}

	public FluidTank setValidator(Predicate<FluidStack> validator)
	{
		return this;
	}

	public boolean isFluidValid(FluidStack stack)
	{
		return true;
	}

	public int getCapacity()
	{
		return 0;
	}

	public FluidStack getFluid()
	{
		return fluid;
	}

	public int getFluidAmount()
	{
		return 0;
	}

	public FluidTank readFromNBT(CompoundTag nbt) {
		return this;
	}

	public CompoundTag writeToNBT(CompoundTag nbt) {
		return nbt;
	}

	public int getTanks() {

		return 1;
	}

	public FluidStack getFluidInTank(int tank) {
		return getFluid();
	}

	public int getTankCapacity(int tank) {
		return getCapacity();
	}

	public boolean isFluidValid(int tank, FluidStack stack) {

		return isFluidValid(stack);
	}

	public int fill(FluidStack resource, IFluidHandler.FluidAction action)
	{
		return 0;
	}

	public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action)
	{
		return fluid;
	}

	public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action)
	{
		return fluid;
	}

	protected void onContentsChanged()
	{

	}

	public void setFluid(FluidStack stack)
	{
		this.fluid = stack;
	}

	public boolean isEmpty()
	{
		return true;
	}

	public int getSpace()
	{
		return 0;
	}
}
