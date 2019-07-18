package com.gk.udmp2;

import com.ximpleware.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XpathParser {


    public List<Object> getXpathValue(String xml, List<XpathModel> schemaList, String... namespace) {
        VTDGen vg = new VTDGen();
        vg.setDoc(xml.getBytes());
        VTDNav vn = null;
        AutoPilot ap = null;

        List<Object> parsedxmlList = new ArrayList<>();
//        System.out.println("getXPathValue");
//        System.out.println(xml);
//        System.out.println(xpathlist);

        try {
            vg.parse(false);
            vn = vg.getNav();
            ap = new AutoPilot(vn);

            for (XpathModel xpathModel : schemaList) {
                String xpath = xpathModel.getXpath();
                //substring to remove " "(quotes) from string value
                xpath = xpath.substring(1, xpath.length() - 1);
                // String xpathVal = "";
                String type = xpathModel.getType();

                ap.selectXPath(xpath);

                if (type == "string") {
                    String xpathVal = "";
                    while (ap.evalXPath() != -1) {
                        int t = vn.getText();
                        if (t != -1) {
                            xpathVal = vn.toNormalizedString(t);
                        }
                    }
                    //for adding as String
                    //parsedxmlList.add(xpathVal);
                    //For adding String value as List
                    parsedxmlList.add(xpathVal);

                }
                if (type == "type_1") {
                    List<String> xpathVal = new ArrayList<>();
                    while (ap.evalXPath() != -1) {

                        int t = vn.getText();
                        if (t != -1) {
//                     System.out.println("Current index ==>" + vn.getCurrentIndex() + " ");
//                     System.out.println("Element Name==> " + vn.toString(vn.getCurrentIndex()));
//                     System.out.println(xpath + " = " + vn.toNormalizedString(vn.getText()));
                            //xpathVal = String.join("~", xpathVal, vn.toNormalizedString(t));
                            xpathVal.add(vn.toNormalizedString(t));
                        }

                    }
//                    if (xpathVal.length() != 0) {
//                        xpathVal = xpathVal.substring(1, xpathVal.length());
//                        parsedxmlList.add(xpathVal);
//                    }
                    parsedxmlList.add(xpathVal.toArray());
                }




//            }
//
//
//        } catch (EOFException e1) {
//            e1.printStackTrace();
//        } catch (NavException e1) {
//            e1.printStackTrace();
//        } catch (EncodingException e1) {
//            e1.printStackTrace();
//        } catch (XPathParseException e1) {
//            e1.printStackTrace();
//        } catch (EntityException e1) {
//            e1.printStackTrace();
//        } catch (ParseException e1) {
//            e1.printStackTrace();
//        } catch (XPathEvalException e) {
//            e.printStackTrace();
//        }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



//       parsedxmlList.forEach(x-> System.out.println(x.getClass()));
//        System.out.println("---------------------------");
        return parsedxmlList;
    }

}

