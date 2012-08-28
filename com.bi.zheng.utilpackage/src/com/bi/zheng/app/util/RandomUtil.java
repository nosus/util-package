package com.bi.zheng.app.util;

/**
 * @author <a href="mailto:bizheng@gmail.com">Zheng BI</a>
 * @version $Id: $
 */
import java.util.Arrays;
import java.util.Random;

/**
 * <code>RandomUtil</code> - Random Tool Class.
 * 
 * @author SageZk
 * @version 1.0
 */
public class RandomUtil {

  private RandomUtil() {}

  private static Random rnd = null;

  /**
   * ��ʼ���������������
   */
  private static void initRnd() {
    if (rnd == null)
      rnd = new Random();
  }

  /**
   * ���㲢�������ظ�ֵ���� <code>min</code> Ϊ���� <code>max</code> Ϊ���޵�����������顣
   * 
   * @param min
   *          ����������ޣ�������
   * @param max
   *          ����������ޣ�������
   * @param len
   *          ������鳤��
   * @return �������
   */
  public static int[] getLotteryArray(int min, int max, int len) {
    // ����У�鼰�����Ż�
    if (len < 0)
      return null; // ����С�� 0 �����鲻����
    if (len == 0)
      return new int[0]; // ���س���Ϊ 0 ������
    if (min > max) { // У������ min max
      int t = min;
      min = max;
      max = t;
    }
    final int LEN = max - min + 1; // ���Ӹ���
    if (len > LEN)
      return null; // ������� 35 ѡ 36 ������ͷ��� null
    // �������ظ�ֵ�������
    initRnd(); // ��ʼ�������������
    int[] seed = new int[LEN]; // ��������
    for (int i = 0, n = min; i < LEN;)
      seed[i++] = n++; // ��ʼ����������
    for (int i = 0, j = 0, t = 0; i < len; ++i) {
      j = rnd.nextInt(LEN - i) + i;
      t = seed[i];
      seed[i] = seed[j];
      seed[j] = t;
    }
    return Arrays.copyOf(seed, len); // ע�⣺copyOf ��Ҫ JRE1.6
  }

  // Unit Testing
  public static void main(String[] args) {
    final int N = 10000; // ���Դ���
    for (int i = 0; i < N; ++i) {
      int[] la = RandomUtil.getLotteryArray(1, 35, 7);
      if (la == null)
        continue;
      for (int v : la)
        System.out.printf("%0$02d ", v);
      System.out.println();
    }
  }
}
