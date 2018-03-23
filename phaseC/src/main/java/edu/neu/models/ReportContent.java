package edu.neu.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportContent implements Serializable{

	List<ReportTuple> matchList1;
	List<ReportTuple> matchList2;
	
	public ReportContent() {
		this.matchList1 = new ArrayList<>();
		this.matchList2 = new ArrayList<>();
	}
	
	public ReportContent(String str, boolean match, boolean isFirst) {
		this();
		if(isFirst)
			this.matchList1.add(new ReportTuple(str, match));
		else
			this.matchList2.add(new ReportTuple(str, match));
	}
	
	public String getResult() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("First File : \n");
		for(ReportTuple rt : matchList1) {
			sb.append(rt);
			sb.append("\n");
		}
		sb.append("Second File : \n");
		for(ReportTuple rt : matchList2) {
			sb.append(rt);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public void appendToResult1(String str, boolean match) {
		this.matchList1.add(new ReportTuple(str, match));
	}
	
	public void appendToResult2(String str, boolean match) {
		this.matchList2.add(new ReportTuple(str, match));
	}
	
	public void appendReportTupleTo1(ReportTuple rt) {
		this.matchList1.add(rt);
	}
	
	public void appendReportTupleTo2(ReportTuple rt) {
		this.matchList2.add(rt);
	}
	
	public void addAll(ReportContent rc) {
		this.matchList1.addAll(rc.matchList1);
		this.matchList2.addAll(rc.matchList2);
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		int listSize1 = matchList1.size();
		out.writeInt(listSize1);
		for(ReportTuple rt : matchList1) {
			out.writeUTF(rt.getData());
			out.writeBoolean(rt.isMatch());
		}
		
		int listSize2 = matchList2.size();
		out.writeInt(listSize2);
		for(ReportTuple rt : matchList2) {
			out.writeUTF(rt.getData());
			out.writeBoolean(rt.isMatch());
		}
		
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		int size1 = in.readInt();
		matchList1 = new ArrayList<>();
		for(int i=0; i<size1; i++) {
			matchList1.add(new ReportTuple(in.readUTF(), in.readBoolean()));
		}
		
		int size2 = in.readInt();
		matchList2 = new ArrayList<>();
		for(int i=0; i<size2; i++) {
			matchList2.add(new ReportTuple(in.readUTF(), in.readBoolean()));
		}
	}
	
}
