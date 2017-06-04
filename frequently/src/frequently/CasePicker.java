package frequently;

import java.util.ArrayList;

/*
 * ����, ����, �ߺ�����, �ߺ����� �� ����� ���� ����� ���� ������ ���� ����ϴ� ����Դϴ�.
 */

public class CasePicker {

	ArrayList<int[]> result;

	// ���� ����
	public int PermutationNumber(int n, int r) {
		int result = 1;
		for (int i = n; i > n - r; i--)
			result *= i;

		return result;
	}

	// ���� ����� ��
	public int[][] Permutation(int n, int r) {
		result = new ArrayList<>();

		int[] temp;
		for (int i = 0; i < n; i++) {
			temp = new int[r];
			temp[0] = i;
			PermutationRecursive(n, r, temp, 1);
		}

		int[][] resultarray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++)
			resultarray[i] = result.get(i);

		return resultarray;
	}

	// ���� ����� ���� ����Լ�
	private void PermutationRecursive(int n, int r, int[] space, int nextpush) {
		boolean flag;

		for (int i = 0; i < n; i++) {
			flag = false;
			for (int j = 0; j < nextpush; j++)
				if (space[j] == i) {
					flag = true;
					break;
				}

			if (flag)
				continue;

			space[nextpush] = i;

			if (nextpush == r - 1)
				result.add(space.clone());
			else
				PermutationRecursive(n, r, space, nextpush + 1);
		}
	}

	// ���� ����
	public int CombinationNumber(int n, int r) {
		int result = 1;
		for (int i = n; i > n - r; i--)
			result *= i;

		for (int i = r; i > 1; i--)
			result /= i;

		return result;
	}

	// ���� ����� ��
	public int[][] Combination(int n, int r) {
		result = new ArrayList<>();

		int[] temp;
		for (int i = 0; i < n; i++) {
			temp = new int[r];
			temp[0] = i;
			CombinationRecursive(n, r, temp, 1);
		}

		int[][] resultarray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++)
			resultarray[i] = result.get(i);

		return resultarray;
	}

	// ���� ����� ���� ��� �Լ�
	private void CombinationRecursive(int n, int r, int[] space, int nextpush) {

		for (int i = space[nextpush - 1] + 1; i < n; i++) {
			
			space[nextpush] = i;

			if (nextpush == r - 1)
				result.add(space.clone());
			else
				CombinationRecursive(n, r, space, nextpush + 1);
		}
	}

	// �ߺ����� ����
	public int DuplicatedPermutationNumber(int n, int r) {
		return (int) Math.pow((double) n, (double) r);
	}

	// �ߺ����� ����� ��
	public int[][] DuplicatedPermutation(int n, int r) {
		result = new ArrayList<>();

		int[] temp;
		for (int i = 0; i < n; i++) {
			temp = new int[r];
			temp[0] = i;
			PermutationDuplicatedRecursive(n, r, temp, 1);
		}

		int[][] resultarray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++)
			resultarray[i] = result.get(i);

		return resultarray;
	}

	// �ߺ����� ����� �� ����� ���� ����Լ�
	private void PermutationDuplicatedRecursive(int n, int r, int[] space, int nextpush) {

		for (int i = 0; i < n; i++) {

			space[nextpush] = i;

			if (nextpush == r - 1)
				result.add(space.clone());
			else
				PermutationDuplicatedRecursive(n, r, space, nextpush + 1);
		}
	}

	// �ߺ����� ����
	public int DuplicatedCombinationNumber(int n, int r) {
		return CombinationNumber(n + r - 1, r);
	}

	// �ߺ����� ����� ��
	public int[][] DuplicatedCombination(int n, int r) {
		result = new ArrayList<>();

		int[] temp;
		for (int i = 0; i < n; i++) {
			temp = new int[r];
			temp[0] = i;
			CombinationDuplicatedRecursive(n, r, temp, 1);
		}

		int[][] resultarray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++)
			resultarray[i] = result.get(i);

		return resultarray;
	}

	// �ߺ����� ����� �� ����� ���� ����Լ�
	private void CombinationDuplicatedRecursive(int n, int r, int[] space, int nextpush) {

		for (int i = space[nextpush - 1]; i < n; i++) {

			space[nextpush] = i;

			if (nextpush == r - 1)
				result.add(space.clone());
			else
				CombinationDuplicatedRecursive(n, r, space, nextpush + 1);
		}

	}
}
