package rule.domain;

public class Function {
	/**
     * �ж�һ���û�TAG�ĵ�Xλ�Ƿ�Ϊ1�������demo,��ʵ�ֺ����Բ�����
     * @param user
     * @param tagBitIndex
     * @return
     */
    public boolean userTagJudge(UserDO user,int tagBitIndex){
    	boolean r =  (user.getUserTag() & ((long)Math.pow(2, tagBitIndex))) > 0;
    	return r;
    }
	
	/**
	 * �ж�һ���û��Ƿ񶩹���ĳ����Ʒ
	 * @param user
	 * @param goodsId
	 * @return
	 */
	public boolean hasOrderGoods(UserDO user,long goodsId){
		//���ģ��һ��
		if(user.getUserId() % 2 == 1){
			return true;
		}else{
			return false;
		}
	}
}
