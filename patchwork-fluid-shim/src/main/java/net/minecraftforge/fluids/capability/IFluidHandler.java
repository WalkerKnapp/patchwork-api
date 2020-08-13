package net.minecraftforge.fluids.capability;

import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public interface IFluidHandler {
	enum FluidAction {
		EXECUTE, SIMULATE;

		public boolean execute() {
			return this == EXECUTE;
		}

		public boolean simulate() {
			return this == SIMULATE;
		}
	}

	/**
	 * Returns the number of fluid storage units ("tanks") available
	 *
	 * @return The number of tanks available
	 */
	int getTanks();

	/**
	 * Returns the FluidStack in a given tank.
	 *
	 * <p>
	 * <strong>IMPORTANT:</strong> This FluidStack <em>MUST NOT</em> be modified. This method is not for
	 * altering internal contents. Any implementers who are able to detect modification via this method
	 * should throw an exception. It is ENTIRELY reasonable and likely that the stack returned here will be a copy.
	 * </p>
	 *
	 * <p>
	 * <strong><em>SERIOUSLY: DO NOT MODIFY THE RETURNED FLUIDSTACK</em></strong>
	 * </p>
	 *
	 * @param tank Tank to query.
	 * @return FluidStack in a given tank. FluidStack.EMPTY if the tank is empty.
	 */
	@Nonnull
	FluidStack getFluidInTank(int tank);

	/**
	 * Retrieves the maximum fluid amount for a given tank.
	 *
	 * @param tank Tank to query.
	 * @return     The maximum fluid amount held by the tank.
	 */
	int getTankCapacity(int tank);

	/**
	 * This function is a way to determine which fluids can exist inside a given handler. General purpose tanks will
	 * basically always return TRUE for this.
	 *
	 * @param tank  Tank to query for validity
	 * @param stack Stack to test with for validity
	 * @return TRUE if the tank can hold the FluidStack, not considering current state.
	 * (Basically, is a given fluid EVER allowed in this tank?) Return FALSE if the answer to that question is 'no.'
	 */
	boolean isFluidValid(int tank, @Nonnull FluidStack stack);

	/**
	 * Fills fluid into internal tanks, distribution is left entirely to the IFluidHandler.
	 *
	 * @param resource FluidStack representing the Fluid and maximum amount of fluid to be filled.
	 * @param action   If SIMULATE, fill will only be simulated.
	 * @return Amount of resource that was (or would have been, if simulated) filled.
	 */
	int fill(FluidStack resource, FluidAction action);

	/**
	 * Drains fluid out of internal tanks, distribution is left entirely to the IFluidHandler.
	 *
	 * @param resource FluidStack representing the Fluid and maximum amount of fluid to be drained.
	 * @param action   If SIMULATE, drain will only be simulated.
	 * @return FluidStack representing the Fluid and amount that was (or would have been, if
	 * simulated) drained.
	 */
	@Nonnull
	FluidStack drain(FluidStack resource, FluidAction action);

	/**
	 * Drains fluid out of internal tanks, distribution is left entirely to the IFluidHandler.
	 * <p/>
	 * This method is not Fluid-sensitive.
	 *
	 * @param maxDrain Maximum amount of fluid to drain.
	 * @param action   If SIMULATE, drain will only be simulated.
	 * @return FluidStack representing the Fluid and amount that was (or would have been, if
	 * simulated) drained.
	 */
	@Nonnull
	FluidStack drain(int maxDrain, FluidAction action);

}
