/*
 *
 */
package wblut.hemesh;

/**
 *
 */
public abstract class HE_Element {

	protected static long _currentKey;
	protected final long _key;
	protected long _labels;


	/**
	 *
	 */
	public HE_Element() {
		_key = _currentKey;
		_currentKey++;
		_labels = mergeLabels(-1, -1);
	}



	private long mergeLabels(final int internal, final int external) {
		return (((long) internal) << 32) | (external & 0xffffffffL);

	}

	/**
	 *
	 *
	 * @param label
	 */
	public final void setInternalLabel(final int label) {
		_labels = mergeLabels(label, getLabel());
	}

	/**
	 *
	 *
	 * @param label
	 */
	public final void setLabel(final int label) {
		_labels = mergeLabels(getInternalLabel(), label);
	}


	/**
	 *
	 *
	 * @return
	 */
	public final long getKey() {
		return _key;
	}

	/**
	 *
	 *
	 * @return
	 */
	public final int getInternalLabel() {
		return (int) (_labels >> 32);

	}

	/**
	 *
	 *
	 * @return
	 */
	public final int getLabel() {
		return (int) _labels;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) (_key ^ (_key >>> 32));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (!(other instanceof HE_Element)) {
			return false;
		}
		return ((HE_Element) other).getKey() == _key;
	}

	/**
	 *
	 *
	 * @param el
	 */
	public void copyProperties(final HE_Element el) {
		_labels = mergeLabels(el.getInternalLabel(), el.getLabel());
	}

	/**
	 *
	 */
	protected abstract void clear();
}