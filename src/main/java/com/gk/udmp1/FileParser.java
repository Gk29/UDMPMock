package com.gk.udmp1;

import com.ximpleware.*;

import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public List<String> parsedata(byte[] ba, String xpath) {
        List<String> datalist = new ArrayList<>();
        VTDGen vg = new VTDGen();
        VTDNav vn = null;
        vg.setDoc(ba);
        AutoPilot ap = null;
        try {
            vg.parse(true);
            vn = vg.getNav();
            ap = new AutoPilot(vn);

        } catch (ParseException e) {
            System.out.println("parse exception");
            e.printStackTrace();
        }

        //ap.selectElementNS("http://www.fpml.org/FpML-5/confirmation", "*");


        try {
            ap.selectXPath("/requestConfirmation/trade");
            int result = ap.evalXPath();
            //System.out.println("Current index ==>" + vn.getCurrentIndex() + " ");
            //System.out.println("Element Name==> " + vn.toString(vn.getCurrentIndex()));
            if (vn.toElement(VTDNav.FIRST_CHILD, "tradeHeader")) {

                if (vn.toElement(VTDNav.FIRST_CHILD, "partyTradeIdentifier")) {
                    String pr = "";
                    String tradeid = "";
                    do {
                        if (vn.toElement(VTDNav.FIRST_CHILD, "partyReference")) {
                            int t = vn.getText();
                            if (t != -1) {//test to see if the element has text node or not
                                String pa=vn.toString(t);
                              //  System.out.println(vn.toString(vn.getCurrentIndex()) + " element's first name ==> " + vn.toString(t));
                                pr=pr+pa+",";
                                System.out.println("pr  ->" + pr);
                            } else {
                                System.out.println(vn.toString(vn.getCurrentIndex()) + " has no text value");
                            }
                        }

                        if (vn.toElement(VTDNav.NEXT_SIBLING, "tradeId")) {

                            int t = vn.getText();
                            if (t != -1) {
                                //test to see if the element has text node or not
                                String tr=vn.toString(t);
                                //  System.out.println(vn.toString(vn.getCurrentIndex()) + " element's first name ==> " + vn.toString(t));
                                tradeid=tradeid+tr+",";
                                //System.out.println("tradeid  ->" + tradeid);

                            } else {
                                System.out.println("null");
                            }
                        }
                        vn.toElement(VTDNav.PARENT);
                    }

                    while (vn.toElement(VTDNav.NEXT_SIBLING, "partyTradeIdentifier"));

                    System.out.println(pr + "   "+tradeid);

                    datalist.add(pr);
                    datalist.add(tradeid);

                    // System.out.println("Current index ==>" + vn.getCurrentIndex() + " ");
                    if (vn.toElement(VTDNav.NEXT_SIBLING, "tradeDate")) {
                        String tradeDate = "";
                        int t = vn.getText();
                        if (t != -1) {//test to see if the element has text node or not
                            tradeDate = vn.toString(t);
                            //  System.out.println(vn.toString(vn.getCurrentIndex()) + " element's first name ==> " + vn.toString(t));
                            datalist.add(tradeDate);

                        } else {
                            System.out.println("null");
                        }

                    }
                    vn.toElement(VTDNav.PARENT);
                    if (vn.toElement(VTDNav.NEXT_SIBLING, "governingLaw")) {
                        String gover_law = "";
                        int t = vn.getText();
                        if (t != -1) {//test to see if the element has text node or not
                            gover_law = vn.toString(t);
                            //   System.out.println(vn.toString(vn.getCurrentIndex()) + " element's first name ==> " + vn.toString(t));
                            datalist.add(gover_law);
                        } else {
                            System.out.println("null");
                        }
                    }

                }

            }


        } catch (PilotException e) {
            System.out.println("pilot exception");
            e.printStackTrace();
        } catch (NavException e) {
            System.out.println("Nav exception");
            e.printStackTrace();
        } catch (XPathParseException e) {
            e.printStackTrace();
        } catch (XPathEvalException e) {
            e.printStackTrace();
        }


        return datalist;
    }
}
