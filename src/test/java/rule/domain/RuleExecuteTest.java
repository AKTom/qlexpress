package rule.domain;

import java.util.ArrayList;
import java.util.List;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;

public class RuleExecuteTest {
	// ����ִ����
	ExpressRunner runner = new ExpressRunner(false, true);

	// ��һЩ��ʼ���Ĳ�������ӹ�������ĺ���
	public void init() throws Exception {
		runner.addOperatorWithAlias("����", "and", null);
		runner.addFunctionOfClassMethod("userTagJudge",
				Function.class.getName(), "userTagJudge", new String[] {
						UserDO.class.getName(), "int" }, "�㲻����������");

		runner.addFunctionOfClassMethod("hasOrderGoods",
				Function.class.getName(), "hasOrderGoods", new String[] {
						UserDO.class.getName(), "long" }, "��û�п�ͨ�Ա�����");

		runner.addMacro("��������", "userTagJudge(userInfo,3)");// 3��ʾ�������ҵı�־λ
		runner.addMacro("ƻ������", "userTagJudge(userInfo,5)");// 5��ʾƻ�����ҵı�־λ
		runner.addMacro("�Ѿ�����", "hasOrderGoods(userInfo,100)");// 100��ʾ������Ʒ��ID
	}

	// ��װִ��
	public String hasPermission(UserDO userInfo, String expression)
			throws Exception {
		IExpressContext<String, Object> expressContext = new DefaultContext<String, Object>();
		expressContext.put("userInfo", userInfo);
		List<String> errorInfo = new ArrayList<String>();
		Boolean result = (Boolean) runner.execute(expression, expressContext,
				errorInfo, true, false);
		String resultStr = "";
		if (result.booleanValue() == true) {
			resultStr = "���Զ�������Ʒ";
		} else {
			for (int i = 0; i < errorInfo.size(); i++) {
				if (i > 0) {
					resultStr = resultStr + ",";
				}
				resultStr = resultStr + errorInfo.get(i);
			}
			resultStr = resultStr + ",���Բ��ܶ�������Ʒ";
		}
		return "�װ���" + userInfo.getName() + " : " + resultStr;
	}

	public static void main(String[] args) throws Exception {
		RuleExecuteTest exe = new RuleExecuteTest();
		exe.init();
		String str = exe.hasPermission(new UserDO(151, "iamzhong", 8),
				"ƻ������ and �Ѿ�����");
		System.out.println(str);
	}

}
