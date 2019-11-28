package com.datacentre.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Host;

public final class Hosts implements Iterable<Host> {

  private final List<Host> hList = new LinkedList<Host>();

	public Hosts(List<? extends Host> hosts) {
		this.hList.addAll(hosts);
	}
	
	public boolean add(Host host){
		return this.hList.add(host);
	}
	
	public boolean remove(Host host2Remove){
		return this.hList.remove(host2Remove);
	}
	

	@Override
	public Iterator<Host> iterator() {
		return get().iterator();
	}

	public List<Host> get() {
		return Collections.unmodifiableList(this.hList);
	}

	public Host getWithMinimumNumberOfPesEquals(int noOfPes) {
		List<Host> hosts = this.orderedAscByAvailablePes().get();

		for (int i = 0; i < hosts.size(); i++) {
			if (hosts.get(i).getNumberOfFreePes() >= noOfPes) {
				return hosts.get(i);
			}
		}
		return null;
	}

	public int size() {
		return this.hList.size();
	}

	public Hosts orderedAscByAvailablePes() {
		List<Host> list = new ArrayList<Host>(this.hList);

		Collections.sort(list, new Comparator<Host>() {
			@Override
			public int compare(Host o1, Host o2) {
				return Integer.valueOf(o1.getNumberOfFreePes()).compareTo(
						o2.getNumberOfFreePes());
			}
		});
		return new Hosts(list);
	}
}