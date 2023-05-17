package ab2.impl.BerishajVojticekKozlov;

import ab2.Ab2;

import ab2.AlgoDatHashMap;

public class Ab2Impl implements Ab2 {

	@Override
	public <K, V> AlgoDatHashMap<K, V> newHashMapLinear(int minSize) {
		// TODO Auto-generated method stub
		return HashMap.linear();
	}

	@Override
	public <K, V> AlgoDatHashMap<K, V> newHashMapQuadratic(int minSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K, V> AlgoDatHashMap<K, V> newHashMapDouble(int minSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int quickselectMax(int[] data, int i) {
		// TODO Auto-generated method stub
		return QuickSelect.quickselectMax(data,i);
	}
}
