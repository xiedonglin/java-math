/*
让我以加密算法为例，在这里面引入MAX随机数就可以增强加密算法的保密程度，是不是很厉害？
举个例子，比如说我们要加密一组三位数，那我们设定一个这样的加密规则：
1.先对每个三位数的个、十和百位数，都加上一个较大的随机数。
2.然后将每位上的数都除以7，用所得的余数代替原有的个、十、百位数；
3.最后将第一位和第三位交换。
这就是一个基本的加密变换过程。
假如说，我们要加密数字625，根据刚才的规则，我们来试试。假设随机数我选择590127。
那百、十和个位分别加上这个随机数，就变成了590133，590129，590132。然后，三位分别除以7求余后得到5，1，4。
最终，我们可以得到加密后的数字就是415。因为加密的人知道加密的规则、求余所用的除数7、除法的商、以及所引入的随机数590127，
所以当拿到415的时候，加密者就可以算出原始的数据是625。是不是很有意思？

所说的加密算法，再缺乏商时，无法完成解密，因为数据被降维了。 
該算法之正對3為數字有效果。
 */
public class EncryAndDecry {
    private static int MAX = 590127;
    private static int MULTIPLE = 7;

    public static void main(String[] args) {
        System.out.println(encryptionNum(345));
        System.out.println(decryptNum(432));
    }

    public static int encryptionNum(int num) {
        System.out.println("加密前：" + num);
        // 1.取余 并 加上随机数
        int bit = num % 10;
        int tenBit = num % 100 / 10;
        int hundredBit = num % 1000 / 100;
        System.out.println(bit + "\t" + tenBit + "\t" + hundredBit);
        bit = bit + MAX;
        tenBit = tenBit + MAX;
        hundredBit = hundredBit + MAX;
        System.out.println(bit + "\t" + tenBit + "\t" + hundredBit);
        // 2.每个位数 除以7 取余代替原来的个十百
        bit = bit % MULTIPLE;
        tenBit = tenBit % MULTIPLE;
        hundredBit = hundredBit % MULTIPLE;
        System.out.println(bit + "\t" + tenBit + "\t" + hundredBit);
        // 3.swap 第一位和第三位
        int temp;
        temp = bit;
        bit = hundredBit;
        hundredBit = temp;
        System.out.println(bit + "\t" + tenBit + "\t" + hundredBit);
        int result = bit + tenBit * 10 + hundredBit * 100;
        System.out.println("加密结果为：" + result);
        return result;
    }

    public static int decryptNum(int num) {
        System.out.println("解密前：" + num);
        // 1.取余
        int bit = num % 10;
        int tenBit = num % 100 / 10;
        int hundredBit = num % 1000 / 100;
        // 2.交互位数
        int temp;
        temp = bit;
        bit = hundredBit;
        hundredBit = temp;
        // 3.乘回7的倍数
        // 这里可能有bug 只能针对当前的MAX值 如果换了一个随机值 要考虑 是否要+1
        int temp2 = (MAX / MULTIPLE);
        if ((MAX % MULTIPLE)!=0) {
            temp2++;
        }
        bit = bit + temp2 * MULTIPLE;
        tenBit = tenBit + temp2 * MULTIPLE;
        hundredBit = hundredBit + temp2 * MULTIPLE;
        // 4.减去随机数
        bit = bit - MAX;
        tenBit = tenBit - MAX;
        hundredBit = hundredBit - MAX;
        System.out.println(bit + "\t" + tenBit + "\t" + hundredBit);
        int result = bit + tenBit * 10 + hundredBit * 100;
        System.out.println("解密结果为：" + result);
        return result;
    }

}
