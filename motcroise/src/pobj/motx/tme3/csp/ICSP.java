package pobj.motx.tme3.csp;

import java.util.List;

public interface ICSP {
	public List<IVariable> getVars();
	public boolean isConsistent();
	public ICSP assign(IVariable vi, String val);
}
