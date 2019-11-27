package com.datacentre.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Host;


public abstract class WaitingHostList implements Iterable<Host> {
	/**
	 * Hosts in waiting list
	 * 
	 *  author Prajwal Gopal
	 */
	private final LinkedList<Host> host_list=new LinkedList<Host>();
	private int hos;
	
	public WaitingHostList(List hosts) {
		this.host_list.addAll((Collection<? extends Host>) hosts);
	}
	public boolean add(Host host) {
		return this.host_list.add(host);
		
	}
	public boolean remove(Host removehost)
	{
		return this.host_list.remove(removehost);
	}
	public Host next() {
		Host host=null;
		if(! host_list.isEmpty()) {
			int index=(this.hos++ % this.host_list.size());
			host=this.host_list.get(index);
			
		}
		return host;
		
		
	}
	@Override
	public Iterator<Host>iterator(){
		return get().iterator();
		
	}
	
	public List<Host>get(){
		return Collections.unmodifiableList(this.host_list);
	}
	
	public Object minimumPES(int npes) {
		List hosts = this.AvailablePes().get();
		for (int i = 0; i < hosts.size(); i++) {
			if (((Host) hosts.get(i)).getNumberOfFreePes() >= npes) {
				return hosts.get(i);
			}
		}
		return null;
	}
	
public int size() {
	return this.host_list.size();
}

public WaitingHostList AvailablePes() {
	ArrayList<Host> list = new ArrayList<Host>(this.host_list);

	Collections.sort(list, new Comparator<Host>() {
		@Override
		public int compare(Host o1, Host o2) {
			return Integer.valueOf(o1.getNumberOfFreePes()).compareTo(
					o2.getNumberOfFreePes());
		}
	});
	return null;
	
}
}

		
	

	
	
	


