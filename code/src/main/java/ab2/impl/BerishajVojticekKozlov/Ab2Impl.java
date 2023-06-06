package ab2.impl.BerishajVojticekKozlov;

import ab2.Ab2;

import static ab2.impl.BerishajVojticekKozlov.Quadratic.getValidCapacityQuadratic;
import static ab2.impl.BerishajVojticekKozlov.Double.getValidCapacity;

public class Ab2Impl implements Ab2 {

	@Override
	public <K, V> ab2.AlgoDatHashMap<K, V> newHashMapLinear(int minSize) {
		// TODO Auto-generated method stub
		return new Linear<>(minSize + 1);
	}

	@Override
	public <K, V> ab2.AlgoDatHashMap<K, V> newHashMapQuadratic(int minSize) {
		// TODO Auto-generated method stub
		minSize = getValidCapacityQuadratic( minSize + 1);
		return new Quadratic<>(minSize);
	}

	@Override
	public <K, V> ab2.AlgoDatHashMap<K, V> newHashMapDouble(int minSize) {
		// TODO Auto-generated method stub
		minSize = getValidCapacity(minSize + 1);
		return new Double<>(minSize);
	}

	@Override
	public int quickselectMax(int[] data, int i) {
		// TODO Auto-generated method stub
		return QuickSelect.quickSelect(data,i);
	}
}
