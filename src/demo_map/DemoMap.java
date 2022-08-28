package demo_map;

import utils.get_set_service.GetService;

import java.util.Map;
import java.util.TreeMap;

public class DemoMap {
    public static void main(String[] args) {
        Map<String, Integer> map =createVoucher();

       for(Map.Entry<String, Integer> v : map.entrySet()) {
           System.out.println(v.getKey() +"\t"+ v.getValue());
       }
    }
    private static Map<String, Integer> createVoucher() {
        Map<String, Integer> voucherList = new TreeMap<>();
        int numberOfVoucher10 = GetService.getNumberInteger("Enter number of voucher 10%: ", 0,100);
        int numberOfVoucher20 = GetService.getNumberInteger("Enter number of voucher 20%: ", 0,100);
        int numberOfVoucher50 = GetService.getNumberInteger("Enter number of voucher 50%: ", 0,100);
        voucherList.put("Voucher10%", numberOfVoucher10);
        voucherList.put("Voucher20%", numberOfVoucher20);
        voucherList.put("Voucher50%", numberOfVoucher50);
        return voucherList;
    }
}
