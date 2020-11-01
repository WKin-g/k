import java.util.Scanner;
//
class Tools{
    int t;
    int u;
    void SHOW_RED(int RED,int RED_MAX){
        int n=(int)(1.0*RED/RED_MAX*10);
        int i;
        System.out.print("<");
        if(n<0){
            n=0;
        }
        for(i=0;i<n;i++){
            System.out.print("◆");
        }
        for(i=0;i<10-n;i++){
            System.out.print("◇");
        }
        System.out.print(">");
    }
}
//以上是工具类：辅助我完成程序。包括两个变量和一个方法：血条显示。
class Outfit{
    String Name;int WEIGHT,SHARPNESS,PROTECTION,SPLASH;
}
class Zero extends  Outfit{
    {
        Name="从零开始";
    }
}
class Frostmourne extends Outfit{
    {
        Name="霜之哀伤";WEIGHT=3;SHARPNESS=3;PROTECTION=3;SPLASH=1;
    }
}
class JK extends Outfit{
    {
        Name="JK制服";PROTECTION=1;SPLASH=10;
    }
}
class Ninja_Suit extends Outfit{
    {
        Name="神奇忍者";WEIGHT=-12;SHARPNESS=5;PROTECTION=3;SPLASH=1;
    }
}
class Dog_Tag extends Outfit{
    {
        Name="狗牌";
    }
}
//以上是装备类：共五种。
class Skill{
    String Name;
    String Description;
    void TRIGGER(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T){ }
}
class NULL extends Skill{
    {
        Name="从无到有";
        Description="什么意思？字面意思。";
    }
}
class Shield extends Skill{
    {
        Name="护盾";
        Description="每回合获得一定的护盾。护盾无法保留。";
    }
    void TRIGGER(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T){
        if(SEQUENCE_Player>=100&&User==0) {
            T.t -=3;
            if (T.t < 0) {
                T.t = 0;
            }
            System.out.println("***"+N_P_C_U.CHECK_Name()+"的技能 "+Name+" 抵挡了"+3+"点伤害。");
        }
        if(SEQUENCE_N_P_C>=100&&User==1&&SEQUENCE_Player<100){
            T.t-=3;
            if (T.t < 0) {
                T.t = 0;
            }
            System.out.println("***"+Player_U.Name+"的技能 "+Name+" 抵挡了"+3+"点伤害。");
        }
    }
}
class Slime_n extends Skill{
    {
        Name="黏液";
        Description="让他身上黏黏的。";
    }
    void TRIGGER(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T){
        if(SEQUENCE_Player>=100&&User==1){
            N_P_C_U.CHANGE_AGI_B(Player_U.ATK_U/3);
            System.out.println("***"+N_P_C_U.CHECK_Name()+"身上变得黏黏的了。总敏捷下降。");
        }
        if(SEQUENCE_N_P_C>=100&&User==0&&SEQUENCE_Player<100){
            Player_U.AGI_B-=N_P_C_U.CHECK_ATK_U()/3;
            System.out.println("***"+Player_U.Name+"身上变得黏黏的了。总敏捷下降。");
        }
    }
}
class Thorns extends Skill{
    {
        Name="荆棘";
        Description="反伤。装备的锋利越高，反伤越高。";
    }
    void TRIGGER(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T){
        if(SEQUENCE_N_P_C>=100&&User==1&&SEQUENCE_Player<100){
            N_P_C_U.BE_ATTACKED(Player_U.Outfit_E.SHARPNESS*N_P_C_U.CHECK_Time());
            System.out.print("***" + N_P_C_U.CHECK_Name() + "受到了");
            if(N_P_C_U.CHECK_Time()>1) {
                System.out.print(N_P_C_U.CHECK_Time() + "次");
            }
            System.out.print(Player_U.Outfit_E.SHARPNESS + "点反伤。");
            System.out.print('\n');
        }
        if(SEQUENCE_Player>=100&&User==0){
            Player_U.RED-=N_P_C_U.CHECK_ATK_U()/10;
            System.out.println("***"+Player_U.Name+"受到了"+N_P_C_U.CHECK_ATK_U()/10+"点反伤。");
        }
    }
}
class Buffering extends Skill{
    {
        Name="卸力";
        Description="减免一半的伤害。不过对总敏捷有一定要求。";
    }
    void TRIGGER(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T){
        if(SEQUENCE_N_P_C>=100&&User==1&&SEQUENCE_Player<100){
            if(Player_U.AGI_U>=24) {
                T.t /= 2;
                System.out.println("***" + Player_U.Name+"卸去了一半的伤害。");
            }else {
                System.out.println("***" + Player_U.Name+"的卸力发动失败。");
            }
        }
        if(SEQUENCE_Player>=100&&User==0){
            if(N_P_C_U.CHECK_AGI_U()>=24) {
                T.t /= 2;
                System.out.println("***" + N_P_C_U.CHECK_Name()+"卸去了一半的伤害。");
            }else{
                System.out.println("***" + N_P_C_U.CHECK_Name()+"的卸力发动失败。");
            }
        }
    }
}
class Strength extends Skill{
    {
        Name="强壮肌肉";
        Description="大大幅提高总攻击。";
    }
    void TRIGGER(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T){
        if(SEQUENCE_N_P_C>=100&&User==0&&SEQUENCE_Player<100){
            N_P_C_U.CHANGE_ATK_B(100);
            System.out.println("***" + N_P_C_U.CHECK_Name()+"捏了捏他的肌肉。");
        }
        if(SEQUENCE_Player>=100&&User==1){
            Player_U.ATK_B+=100;
            System.out.println("***" + Player_U.Name+"捏了捏他的肌肉。");
        }
    }
}
//以上是技能类：共六种。无论是NPC还是玩家，都能通过引用技能以使用。
class Player{
    Tools T=new Tools();
    String Name;
    boolean ALIVE;
    int EXP,LEVEL;
    int VIT,ATK,DEF,AGI,LUCK;//五种属性。
    int PRA;//熟练度。
    int ATK_B,DEF_B,AGI_B;//B即BUFF。用来完成 非永久BUFF 的效果。
    int RED_MAX,ATK_U,DEF_U,AGI_U;//RED_MAX即最大血量。U即Ultimate。用公式计算的总属性。详见方法：REFRESH。
    int RED;//即当前血量。
    {
        LEVEL=1;//等级初始为1；
    }
    Player(String Na){
        Name=Na;
    }//构造器。命名。
    Outfit Outfit_E;//装备栏。
    Skill Skill_E;//技能栏。
    void REFRESH(){//方法：更新总属性。
        RED_MAX=(int)(VIT*3*LEVEL*(1+PRA/50.0));
        ATK_U=(int)((ATK*LEVEL*(1+PRA/30.0)+Outfit_E.SHARPNESS)*(ATK_B/100.0));
        DEF_U=(int)((DEF*LEVEL*(1+PRA/30.0)+Outfit_E.PROTECTION)*(DEF_B/100.0));
        AGI_U=(int)((AGI*LEVEL*(1+PRA/30.0)-Outfit_E.WEIGHT)*(AGI_B/100.0));
    }
    void RECOVER(){//方法：完全恢复。
        ALIVE=true;
        ATK_B=100;DEF_B=100;AGI_B=100;
        REFRESH();
        RED=RED_MAX;
    }
    int CHECK_EXP_N(){//方法：检索当前升级需要的经验。
        int EXP_N;
        switch(LEVEL){
            case 1:
                EXP_N=10;
                break;
            case 2:
                EXP_N=30;
                break;
            case 3:
                EXP_N=80;
                break;
            case 4:
                EXP_N=200;
                break;
            default:
                EXP_N=211352;
        }
        return EXP_N;
    }
    void LEVEL_UP(){//方法：升级。
        if(LEVEL<5&&EXP>=CHECK_EXP_N()){
            int RED_DFF=RED_MAX-RED;
            EXP-=CHECK_EXP_N();
            LEVEL++;
            REFRESH();
            RED=RED_MAX-RED_DFF;
            System.out.println("现在你"+LEVEL+"级了！");
        }else if(LEVEL>=5){
            System.out.println("你已经满级了！");
        }else{
            System.out.println("经验值不足！");
        }
    }
    void CHECK(){//方法：查看玩家信息。
        System.out.println("#你的信息");
        if(Outfit_E.Name.equals("JK制服")){
            System.out.print("女装");
        }
        System.out.println(Name+"~经验="+EXP+" 等级="+LEVEL+" 耐力="+VIT+" 攻击="+ATK+" 防御="+DEF+" 敏捷="+AGI+" 熟练度="+PRA);
        System.out.println("总攻击="+ATK_U+" 总防御="+DEF_U+" 总敏捷="+AGI_U);
        System.out.println("装备 "+Outfit_E.Name+"~重量="+Outfit_E.WEIGHT+" 锋利="+Outfit_E.SHARPNESS+" 保护="+Outfit_E.PROTECTION+" 溅射="+Outfit_E.SPLASH);
        System.out.println("技能 "+Skill_E.Name+"~"+Skill_E.Description);
        System.out.print("当前血量"+RED+"/"+RED_MAX);
        T.SHOW_RED(RED,RED_MAX);
        System.out.print('\n');//int ATK_B,DEF_B,AGI_B;
        if(ATK_B!=100||DEF_B!=100||AGI_B!=100){
            System.out.print("由于特殊效果，你的");
            if(ATK_B!=100){
                System.out.print("总攻击变为"+ATK_B+"%，");
            }
            if(DEF_B!=100){
                System.out.print("总防御变为"+DEF_B+"%，");
            }
            if(AGI_B!=100){
                System.out.print("总敏捷变为"+AGI_B+"%，");
            }
            System.out.print("可通过祈祷复原。");
            System.out.print('\n');
        }
    }
    void RESET() {//方法：重置基本属性。
        System.out.println("当前：耐力=" + VIT + " 攻击=" + ATK + " 防御=" + DEF + " 敏捷=" + AGI);
        Scanner in = new Scanner(System.in);
        System.out.println("幸运=25-耐力-攻击-防御-敏捷");
        int VI, AT, DE, AG;
        do {
            System.out.println("显然，属性之和<25");
            System.out.print("耐力 攻击 防御 敏捷：");
            VI = in.nextInt();
            AT = in.nextInt();
            DE = in.nextInt();
            AG = in.nextInt();
            in.nextLine();
        } while (VI + AT + DE + AG > 25);
        VIT = VI;
        ATK = AT;
        DEF = DE;
        AGI = AG;
        LUCK = 25 - VI - AT - DE - AG;
        REFRESH();
        System.out.println("你的属性已更新。");
    }
}
//以上是玩家类。更详细的注释已写在上面。
class N_P_C implements ENEMY{
    String Name;
    boolean ALIVE;
    int LEVEL;
    int VIT,ATK,DEF,AGI;
    int AGI_B,ATK_B;
    int RED_MAX,ATK_U,DEF_U,AGI_U;
    int RED;
    int Time;//连击：指每回合可攻击的次数。
    int EXP;//即击杀此NPC后玩家获得的经验。
    {
        Time=1;//大多数NPC没有连击。
    }
    N_P_C(){
        RECOVER();
    }
    N_P_C(String Na,int Le,int VI,int AT,int DE,int AG,int Ti,int EX,Skill S_E){
        Name=Na;
        LEVEL=Le;
        VIT=VI;
        ATK=AT;
        DEF=DE;
        AGI=AG;
        Time=Ti;
        EXP=EX;
        Skill_E=S_E;
        RECOVER();
    }
    public void REFRESH(){
        RED_MAX=VIT*3*LEVEL;
        ATK_U=(int)(ATK*LEVEL*(ATK_B/100.0));DEF_U=DEF*LEVEL;AGI_U=(int)(AGI*LEVEL*(AGI_B/100.0));
    }
    void RECOVER(){
        ATK_B=100;
        AGI_B=100;
        ALIVE=true;
        REFRESH();
        RED=RED_MAX;
    }
    Skill Skill_E;//每个NPC都有一个技能。
    //
    public void BE_ATTACKED(int VALUE){
        RED-=VALUE;
    }
    public boolean IS_ALIVE(){
        if(RED<=0){
            ALIVE=false;
        }
        return ALIVE;
    }
    public void CHANGE_ATK_B(int VALUE){
        ATK_B+=VALUE;
    }
    public int CHECK_Time(){ return Time;}
    public void CHANGE_AGI_B(int PERCENT){
        AGI_B-=PERCENT;
    }
    public int CHECK_AGI_U(){
        return AGI_U;
    }
    public int CHECK_EXP(){
        return EXP;
    }
    public String CHECK_Name(){
        return Name;
    }
    public int CHECK_RED(){
        return RED;
    }
    public int CHECK_RED_MAX(){
        return RED_MAX;
    }
    public int CHECK_DEF_U(){
        return DEF_U;
    }
    public int CHECK_ATK_U(){
        return ATK_U;
    }
    public String CHECK_Skill_Name(){
        return Skill_E.Name;
    }
    public void Skill(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T){
        Skill_E.TRIGGER(SEQUENCE_Player,SEQUENCE_N_P_C,N_P_C_U,Player_U,User,T);
        //以上是为了满足接口
    }
}
class Soldier extends N_P_C{
    {
        Skill_E=new Shield();
        Name="人类守卫";LEVEL=1;VIT=3;ATK=8;DEF=2;AGI=8;EXP=5;
    }
}
class Slime extends N_P_C{
    {
        Skill_E=new Slime_n();
        Name="史莱姆";LEVEL=2;VIT=3;ATK=9;DEF=3;AGI=5;EXP=10;
    }
}
class Moa extends N_P_C{
    {
        Skill_E=new Buffering();
        Name="恐鸟";LEVEL=3;VIT=60;ATK=8;DEF=3;AGI=3;EXP=50;
    }
}
class Bird extends N_P_C{
    {
        Skill_E=new Buffering();
        Name="大鸟";LEVEL=4;VIT=2;ATK=6;DEF=8;AGI=8;EXP=50;Time=8;
    }
}
class Spartan extends N_P_C{
    {
        Skill_E=new Strength();
        Name="斯巴达狂战士";LEVEL=5;VIT=5;ATK=4;DEF=8;AGI=5;EXP=200;Time=2;
    }
}
//以上是NPC类：更详细的注释已写在上面。NPC的属性与玩家大同小异。
interface ENEMY{
    void CHANGE_ATK_B(int VALUE);
    void REFRESH();
    int CHECK_Time();
    void CHANGE_AGI_B(int PERCENT);
    void BE_ATTACKED(int VALUE);
    boolean IS_ALIVE();
    int CHECK_AGI_U();
    int CHECK_EXP();
    String CHECK_Name();
    int CHECK_RED();
    int CHECK_RED_MAX();
    int CHECK_DEF_U();
    int CHECK_ATK_U();
    String CHECK_Skill_Name();
    void Skill(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T);
}
//以上为接口。
public class Orc {
    public static void main(String[] args){
        Skill S_E=null;
        int SET=1;
        boolean CHECK_CHEAT=false;
        boolean CHECK_JK=false;
        int RESET_FREE=5;
        int EXP_SUM;
        boolean LAST_SEQUENCE;
        int N_P_C_TURN=0;
        int N_P_C_RED_MIN=0;
        int N_P_C_BE_CHOSEN;
        boolean IS_SEQUENCE_N_P_C;
        int SEQUENCE_Player;
        int[] SEQUENCE_N_P_C;
        boolean IS_ALIVE;
        boolean[] BINGO_O=new boolean[5];
        boolean[] BINGO_S=new boolean[5];
        ENEMY[] ENEMY_U=null;
        int N_N=0;
        int LEVEL;
        Tools T=new Tools();
        boolean CONTINUE=true;
        boolean READY=false;
        Outfit[] Outfits=new Outfit[8];
        Outfits[0]=new Zero();
        int O_EMPTY=1;
        Skill[] Skills=new Skill[8];
        Skills[0]=new NULL();
        int S_EMPTY=1;
        //以上是用到的所有变量。
        Scanner in=new Scanner(System.in);
        System.out.print("你的名字：");
        Player Player_U=new Player(in.nextLine());
        Player_U.Outfit_E=Outfits[0];
        Player_U.Skill_E=Skills[0];
        Player_U.RESET();
        Player_U.RECOVER();
        //以上是创建玩家。
        System.out.println("本程序依靠[Enter]交互，请经常[Enter]。如果感觉啰嗦，长按[Enter]可快速过场。");
        in.nextLine();
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        Player_U.CHECK();
        while(CONTINUE){
            while(!READY){
                do{
                    System.out.println("#主界面");
                    System.out.println("1.Lok'tar Ogar(战斗)！  2.你的信息  3.你的装备  4.你的技能  5.祈祷  6.设置  7.先来这里看看  8.结束");
                    T.t=in.nextInt();
                    in.nextLine();
                }while(!(T.t>=1&&T.t<=8));
                switch (T.t) {
                    case 1:
                        if(Player_U.ALIVE) {
                            READY=true;
                            System.out.println("不胜利，毋宁死！");
                        }else {
                            System.out.println("你已无生命特征。请在主界面祈祷以复活。");
                        }
                        break;
                    case 2:
                        Player_U.CHECK();
                        if(Player_U.ALIVE) {
                            do {
                                System.out.println("1.如果经验足够，就可以进行“升级”了。当前需要" + Player_U.CHECK_EXP_N() + "点经验。");
                                System.out.println("2.想洗点吗？需要花费" + Player_U.LEVEL * Player_U.LEVEL * 3 + "点经验。");
                                System.out.println("3.再见。");
                                T.t = in.nextInt();
                                in.nextLine();
                            } while (!(T.t >= 1 && T.t <= 3));
                            switch (T.t) {
                                case 1:
                                    Player_U.LEVEL_UP();
                                    break;
                                case 2:
                                    if (Player_U.EXP >= Player_U.LEVEL * Player_U.LEVEL * 3) {
                                        Player_U.EXP -= Player_U.LEVEL * Player_U.LEVEL * 3;
                                        Player_U.RESET();
                                    } else {
                                        System.out.println("经验值不足！");
                                    }
                                    break;
                                case 3:
                                    System.out.println("按下[Enter]以继续。");
                                    break;
                            }
                        }else {
                            System.out.println("你已无生命特征。请在主界面祈祷以复活。");
                        }
                        break;
                    case 3:
                        System.out.println("#你的装备");
                        for(O_EMPTY=0;Outfits[O_EMPTY]!=null;O_EMPTY++);//检测空的装备格，
                        T.t=0;
                        while(Outfits[T.t]!=null){
                            System.out.println(T.t+"."+Outfits[T.t].Name);
                            T.t++;
                        }
                        do {
                            System.out.println("输入序号即可装备。在“你的信息”中可查看装备详情。");
                            T.t = in.nextInt();
                            in.nextLine();
                        } while (!(T.t >= 0 && T.t < O_EMPTY));
                        Player_U.Outfit_E=Outfits[T.t];
                        Player_U.REFRESH();
                        System.out.println("你装备了"+Player_U.Outfit_E.Name+"。");
                        break;
                    case 4:
                        System.out.println("#你的技能");
                        for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);//检测空的技能格。
                        T.t=0;
                        while(Skills[T.t]!=null){
                            System.out.println(T.t+"."+Skills[T.t].Name);
                            T.t++;
                        }
                        do {
                            System.out.println("输入序号即可携带。在“你的信息”中可查看技能详情。");
                            T.t = in.nextInt();
                            in.nextLine();
                        } while (!(T.t >= 0 && T.t < S_EMPTY));
                        Player_U.Skill_E=Skills[T.t];
                        Player_U.REFRESH();
                        System.out.println("你携带了"+Player_U.Skill_E.Name+"。");
                        break;
                    case 5:
                        System.out.println("#祈祷");
                        do{
                            System.out.println("你可以花费"+Player_U.LEVEL*Player_U.LEVEL*3+"点经验以完全恢复。");
                            System.out.println("1.确定  2.取消");
                            T.t=in.nextInt();
                            in.nextLine();
                        }while(!(T.t>=1&&T.t<=2));
                        if(T.t==1){
                            if (Player_U.EXP >= Player_U.LEVEL * Player_U.LEVEL * 3) {
                                Player_U.EXP-=Player_U.LEVEL * Player_U.LEVEL * 3;
                                Player_U.RECOVER();
                                System.out.println("你完全恢复了。");
                            }else{
                                System.out.println("经验值不足！");
                                System.out.println("你可以输入 I love CQUPT 以完全恢复。");
                                if(in.nextLine().equals("I love CQUPT")){
                                    Player_U.RECOVER();
                                    System.out.println("你完全恢复了。");
                                }else{
                                    System.out.println("输入错误!");
                                }
                            }
                        }else {
                            System.out.println("按下[Enter]以继续。");
                        }
                        break;
                    case 6:
                        do{
                            System.out.println("对战多个敌人时，你希望： 1.自动攻击血量较少的敌人（推荐）  2.由我自己选择目标");
                            SET=in.nextInt();
                            in.nextLine();
                        }while(!(SET>=1&&SET<=2));
                        System.out.println("设置成功。");
                        break;
                    case 7:
                        System.out.println("#帮助");
                        do{
                            System.out.print("1.顺序值介绍  2.溅射介绍  3.彩蛋介绍  4.来点福利？");
                            if(RESET_FREE==5){
                                System.out.print("  5.一次免费的洗点");
                            }
                            System.out.print('\n');
                            T.t=in.nextInt();
                            in.nextLine();
                        }while(!(T.t>=1&&T.t<=RESET_FREE));
                        switch (T.t){
                            case 1:
                                System.out.println("#顺序值介绍：即行动条。对于每个单位：每个单位时间(顺序值+=总敏捷)，当顺序值>=100，进入此单位回合并将顺序值清零。");
                                break;
                            case 2:
                                System.out.println("#溅射介绍：即AOE。对除了攻击目标的其他敌方目标造成伤害。");
                                break;
                            case 3:
                                System.out.println("#彩蛋介绍：其实祈祷可以白嫖。");
                                break;
                            case 4:
                                if(!CHECK_JK) {
                                    System.out.println("您的JK制服已到账。");
                                    for (O_EMPTY = 0; Outfits[O_EMPTY] != null; O_EMPTY++) ;
                                    Outfits[O_EMPTY] = new JK();
                                    CHECK_JK=true;
                                }else{
                                    if(!CHECK_CHEAT) {
                                        do {
                                            System.out.println("开启内置挂？将等级强制设定为10级。我的扣扣：487666740。");
                                            System.out.println("1.确定  2.取消");
                                            T.t = in.nextInt();
                                            in.nextLine();
                                        } while (!(T.t >= 1 && T.t <= 2));
                                        if (T.t == 1) {
                                            Player_U.LEVEL = 10;
                                            Player_U.REFRESH();
                                            CHECK_CHEAT=true;
                                            System.out.println("现在你" + Player_U.LEVEL + "级了！");
                                        } else {
                                            System.out.println("按下[Enter]以继续。");
                                        }
                                    }else {
                                        System.out.println("我一点也没有了。我的扣扣：487666740。");
                                    }
                                }
                                break;
                            case 5:
                                System.out.println("WKing_的配点是5 10 5 5。放弃了幸运（并不是说幸运无用）。");
                                Player_U.RESET();
                                RESET_FREE--;
                                System.out.println("就这一次哦。");
                                break;
                        }
                        break;
                    case 8:
                        return;
                }
                in.nextLine();
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
            do{
                System.out.print("建议多少级就打多少关，请选择关卡（1~5,6为自定义关卡）：");
                LEVEL=in.nextInt();
                in.nextLine();
            }while(!(LEVEL>=1&&LEVEL<=6));
            switch (LEVEL) {
                case 1:
                    N_N=1;
                    ENEMY_U=new ENEMY[N_N];
                    ENEMY_U[0] = new Soldier();
                    break;
                case 2:
                    N_N=3;
                    ENEMY_U=new ENEMY[N_N];
                    ENEMY_U[0]=new Slime();
                    ENEMY_U[1]=new Slime();
                    ENEMY_U[2]=new Slime();
                    break;
                case 3:
                    N_N=1;
                    ENEMY_U=new ENEMY[N_N];
                    ENEMY_U[0]=new Moa();
                    break;
                case 4:
                    N_N=4;
                    ENEMY_U=new ENEMY[N_N];
                    ENEMY_U[0]=new Bird();
                    ENEMY_U[1]=new Bird();
                    ENEMY_U[2]=new Bird();
                    ENEMY_U[3]=new Bird();
                    break;
                case 5:
                    N_N=2;
                    ENEMY_U=new ENEMY[N_N];
                    ENEMY_U[0]=new Spartan();
                    ENEMY_U[1]=new Slime();
                    break;
                case 6:
                    String Na;int Le,VI,AT,DE,AG,Ti,EX;
                    System.out.print("此为自定义关卡，请输入NPC数量：");
                    N_N=in.nextInt();
                    in.nextLine();
                    ENEMY_U=new ENEMY[N_N];
                    for(T.t=0;T.t<N_N;T.t++) {
                        System.out.println(">>>现在构建第"+(T.t+1)+"个NPC。");
                        System.out.print("请输入NPC的名字：");
                        Na = in.nextLine();
                        System.out.print("请输入NPC的等级 耐力 攻击 防御 敏捷 连击(没有则为1) 击杀后玩家获得的经验：");
                        Le=in.nextInt();VI= in.nextInt();AT= in.nextInt();DE= in.nextInt();AG= in.nextInt();Ti= in.nextInt();EX= in.nextInt();
                        in.nextLine();
                        do{
                            System.out.println("请选择NPC的技能： 1.护盾 2.黏液 3.荆棘 4.卸力 5.强壮肌肉");
                            T.u= in.nextInt();
                            in.nextLine();
                        }while(!(T.u>=1&&T.u<=5));
                        switch (T.u){
                            case 1:
                                S_E=new Shield();
                                break;
                            case 2:
                                S_E=new Slime_n();
                                break;
                            case 3:
                                S_E=new Thorns();
                                break;
                            case 4:
                                S_E=new Buffering();
                                break;
                            case 5:
                                S_E=new Strength();
                        }
                        ENEMY_U[T.t]=new N_P_C(Na,Le,VI,AT,DE,AG,Ti,EX,S_E);
                    }
                    System.out.println("构建完成。");
                    in.nextLine();
            }
            //以下为战斗系统。通用性很强的战斗系统。随意多的NPC数量，随意多的NPC种类。
            //并不是单纯的回合制。引用了行动条机制。详见游戏内的“先来这里看看”菜单。
            //大量的代码用来优化细节。
            SEQUENCE_N_P_C=new int[N_N];
            System.out.println("你发现了敌人。");
            for(T.t=0;T.t<N_N;T.t++) {
                System.out.print(ENEMY_U[T.t].CHECK_Name() + "~血量=" + ENEMY_U[T.t].CHECK_RED_MAX() + " 总攻击=" + ENEMY_U[T.t].CHECK_ATK_U() + " 总防御=" + ENEMY_U[T.t].CHECK_DEF_U() + " 总敏捷=" + ENEMY_U[T.t].CHECK_AGI_U() + " 技能：" + ENEMY_U[T.t].CHECK_Skill_Name());
                if(ENEMY_U[T.t].CHECK_Time()>1){
                    System.out.print(" 连击="+ENEMY_U[T.t].CHECK_Time());
                }
                System.out.print('\n');
            }
            //以上显示所有NPC的简单信息。
            System.out.print("你的血量："+Player_U.RED+"/"+Player_U.RED_MAX);
            T.SHOW_RED(Player_U.RED,Player_U.RED_MAX);
            System.out.print('\n');
            System.out.println("按下[Enter]以继续。");
            in.nextLine();
            IS_ALIVE=true;
            SEQUENCE_Player=0;
            for(T.t=0;T.t<N_N;T.t++){
                SEQUENCE_N_P_C[T.t]=0;
            }
            //清零所有单位的顺序值。
            while(Player_U.ALIVE&&IS_ALIVE){
                IS_SEQUENCE_N_P_C=true;
                LAST_SEQUENCE=false;
                for(T.t=0;T.t<N_N;T.t++){
                    if(ENEMY_U[T.t].IS_ALIVE()&&SEQUENCE_N_P_C[T.t]>=100){
                        N_P_C_TURN=T.t;
                        LAST_SEQUENCE=true;
                        IS_SEQUENCE_N_P_C=false;
                    }
                }
                //以上：当有多个NPC顺序值达到了100，则停止顺序值的累加，先让所有顺序值达到100的NPC开始回合。
                while(SEQUENCE_Player<100&&IS_SEQUENCE_N_P_C){
                    SEQUENCE_Player+=Player_U.AGI_U;
                    for(T.t=0;T.t<N_N;T.t++){
                        if(ENEMY_U[T.t].IS_ALIVE()) {
                            SEQUENCE_N_P_C[T.t] += ENEMY_U[T.t].CHECK_AGI_U();
                            if (SEQUENCE_N_P_C[T.t] >= 100) {
                                N_P_C_TURN=T.t;
                                IS_SEQUENCE_N_P_C = false;
                            }
                        }
                    }
                }
                //以上：累加顺序值。
                if(!(SEQUENCE_Player<100&&LAST_SEQUENCE)) {
                    System.out.print(Player_U.Name + "的顺序值=" + SEQUENCE_Player + "  V.S  ");
                    for (T.t = 0; T.t < N_N; T.t++) {
                        if (ENEMY_U[T.t].IS_ALIVE()) {
                            System.out.print(T.t+"."+ENEMY_U[T.t].CHECK_Name() + "的顺序值=" + SEQUENCE_N_P_C[T.t] + "  ");
                        }
                    }
                    System.out.print("\n");
                }
                //以上：显示当前顺序值。
                if(SEQUENCE_Player>=100){
                    if(SET==1){
                        for(T.t=0;T.t<N_N;T.t++){
                            if(ENEMY_U[T.t].IS_ALIVE()){
                                N_P_C_RED_MIN=T.t;
                                break;
                            }
                        }
                        for(T.t=0;T.t<N_N;T.t++){
                            if(ENEMY_U[T.t].IS_ALIVE()&&(ENEMY_U[T.t].CHECK_RED()<ENEMY_U[N_P_C_RED_MIN].CHECK_RED())){
                                N_P_C_RED_MIN=T.t;
                            }
                        }
                        N_P_C_BE_CHOSEN=N_P_C_RED_MIN;
                        //以上：设置：自动攻击血量较少的敌人。
                    }else{
                        do{
                            System.out.print("请输入你要进攻的序号：");
                            N_P_C_BE_CHOSEN=in.nextInt();
                            in.nextLine();
                        }while(!(N_P_C_BE_CHOSEN>=0&&N_P_C_BE_CHOSEN<=N_N-1));
                    }
                    //以上：设置：玩家自行选择目标。
                    T.t=Player_U.ATK_U-ENEMY_U[N_P_C_BE_CHOSEN].CHECK_DEF_U();//计算扣血。
                    if(T.t<1){
                        T.t=1;
                    }
                    System.out.println("***你的动作 "+Player_U.Name+"对"+ENEMY_U[N_P_C_BE_CHOSEN].CHECK_Name()+"造成了"+T.t+"点伤害***");
                    Player_U.Skill_E.TRIGGER(SEQUENCE_Player,SEQUENCE_N_P_C[N_P_C_BE_CHOSEN],ENEMY_U[N_P_C_BE_CHOSEN],Player_U,1,T);//玩家的技能发动。
                    ENEMY_U[N_P_C_BE_CHOSEN].Skill(SEQUENCE_Player,SEQUENCE_N_P_C[N_P_C_BE_CHOSEN],ENEMY_U[N_P_C_BE_CHOSEN],Player_U,0,T);//NPC的技能发动。
                    ENEMY_U[N_P_C_BE_CHOSEN].BE_ATTACKED(T.t);
                    if(Player_U.Outfit_E.SPLASH!=0) {
                        for (T.t = 0; T.t < N_N; T.t++) {
                            if (T.t != N_P_C_BE_CHOSEN) {
                                ENEMY_U[T.t].BE_ATTACKED(Player_U.Outfit_E.SPLASH);
                                System.out.print("***" + ENEMY_U[T.t].CHECK_Name() + "受到了" + Player_U.Outfit_E.SPLASH + "点溅射伤害。");
                                System.out.print("\n");
                            }
                        }
                    }
                    //此为溅射效果的实现。
                    SEQUENCE_Player=0;//顺序值清零。
                    //以上：玩家回合。
                }else if(SEQUENCE_N_P_C[N_P_C_TURN]>=100){
                    T.u=ENEMY_U[N_P_C_TURN].CHECK_ATK_U()-Player_U.DEF_U;
                    if(T.u<1){
                        T.u=1;
                    }
                    T.t=ENEMY_U[N_P_C_TURN].CHECK_Time()*T.u;
                    System.out.print("***对方动作 "+ENEMY_U[N_P_C_TURN].CHECK_Name()+"对"+Player_U.Name+"造成了");
                    if(ENEMY_U[N_P_C_TURN].CHECK_Time()>1){
                        System.out.print(ENEMY_U[N_P_C_TURN].CHECK_Time()+"次");//若有连击，显示连击。
                    }
                    System.out.print(T.u+"点伤害***");
                    System.out.print('\n');
                    Player_U.Skill_E.TRIGGER(SEQUENCE_Player,SEQUENCE_N_P_C[N_P_C_TURN],ENEMY_U[N_P_C_TURN],Player_U,1,T);
                    ENEMY_U[N_P_C_TURN].Skill(SEQUENCE_Player,SEQUENCE_N_P_C[N_P_C_TURN],ENEMY_U[N_P_C_TURN],Player_U,0,T);
                    Player_U.RED-=T.t;
                    SEQUENCE_N_P_C[N_P_C_TURN]=0;
                }
                //以上：NPC回合。
                System.out.print(Player_U.Name+"剩余血量："+Player_U.RED+"/"+Player_U.RED_MAX);
                T.SHOW_RED(Player_U.RED,Player_U.RED_MAX);
                System.out.print('\n');
                for(T.t=0;T.t<N_N;T.t++) {
                    System.out.print(ENEMY_U[T.t].CHECK_Name() + "剩余血量：" + ENEMY_U[T.t].CHECK_RED() + "/" + ENEMY_U[T.t].CHECK_RED_MAX());
                    T.SHOW_RED(ENEMY_U[T.t].CHECK_RED(), ENEMY_U[T.t].CHECK_RED_MAX());
                }
                System.out.print('\n');
                //以上：显示当前血量。
                if(Player_U.RED<=0){
                    Player_U.ALIVE=false;
                }
                IS_ALIVE=false;
                for(T.t=0;T.t<N_N;T.t++){
                    if(ENEMY_U[T.t].IS_ALIVE()){
                        ENEMY_U[T.t].REFRESH();
                        IS_ALIVE=true;
                    }
                }
                Player_U.REFRESH();
                in.nextLine();
                //刷新所有角色的状态。
            }
            if(Player_U.ALIVE){
                System.out.println("*****U Survived*****");
                switch (LEVEL){
                    case 1:
                        if(!BINGO_O[0]) {
                            for(O_EMPTY=0;Outfits[O_EMPTY]!=null;O_EMPTY++);
                            Outfits[O_EMPTY] = new Frostmourne();
                            System.out.println(">>>霜之哀伤？怎么会在这儿？……");
                            System.out.println(">>>你获得了一件装备。");
                            BINGO_O[0]=true;
                        }
                        if(!BINGO_S[0]) {
                            if (Player_U.Skill_E.Name.equals("从无到有")) {
                                for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);
                                Skills[S_EMPTY] = new Shield();
                                System.out.println(">>>你的技能 从无到有 发动。");
                                System.out.println(">>>你获得了一个技能。");
                                BINGO_S[0] = true;
                            }
                        }
                        break;
                    case 2:
                        if(!BINGO_S[1]) {
                            for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);
                            if (Player_U.Skill_E.Name.equals("从无到有")) {
                                Skills[S_EMPTY] = new Slime_n();
                                System.out.println(">>>你的技能 从无到有 发动。");
                                System.out.println(">>>你获得了一个技能。");
                                BINGO_S[1] = true;
                            }
                        }
                        break;
                    case 3:
                        if(!BINGO_O[2]){
                            for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);
                            Skills[S_EMPTY]=new Thorns();
                            System.out.println(">>>你发现了一具尸骸。是个人类。");
                            System.out.println(">>>你在他身上搜出了一本书。");
                            System.out.println(">>>你获得了一个技能。");
                            BINGO_O[2]=true;
                        }
                        if(!BINGO_S[2]) {
                            for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);
                            if (Player_U.Skill_E.Name.equals("从无到有")) {
                                Skills[S_EMPTY] = new Buffering();
                                System.out.println(">>>你的技能 从无到有 发动。");
                                System.out.println(">>>你获得了一个技能。");
                                BINGO_S[2] = true;BINGO_S[3]=true;
                            }
                        }
                        break;
                    case 4:
                        if(!BINGO_O[3]){
                            for(O_EMPTY=0;Outfits[O_EMPTY]!=null;O_EMPTY++);
                            Outfits[O_EMPTY]=new Ninja_Suit();
                            System.out.println(">>>如此壮硕的鸟居然会短暂飞行。");
                            System.out.println(">>>你剥下了他们的皮毛。做了件装备。");
                            System.out.println(">>>你获得了一件装备。");
                            BINGO_O[3]=true;
                        }
                        if(!BINGO_S[3]) {
                            for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);
                            if (Player_U.Skill_E.Name.equals("从无到有")) {
                                Skills[S_EMPTY] = new Buffering();
                                System.out.println(">>>你的技能 从无到有 发动。");
                                System.out.println(">>>你获得了一个技能。");
                                BINGO_S[2] = true;BINGO_S[3]=true;
                            }
                        }
                        break;
                    case 5:
                        if(!BINGO_O[4]){
                            for(O_EMPTY=0;Outfits[O_EMPTY]!=null;O_EMPTY++);
                            Outfits[O_EMPTY]=new Dog_Tag();
                            System.out.println(">>>Lok'tar Ogar!不胜利，毋宁死！");
                            System.out.println(">>>你拽下了这个战士的狗牌。");
                            System.out.println(">>>或许你应该去找这个鬼地方的建造者。");
                            BINGO_O[4]=true;
                        }
                        if(!BINGO_S[4]) {
                            for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);
                            if (Player_U.Skill_E.Name.equals("从无到有")) {
                                Skills[S_EMPTY] = new Strength();
                                System.out.println(">>>你的技能 从无到有 发动。");
                                System.out.println(">>>你获得了一个技能。");
                                BINGO_S[4] = true;
                            }
                        }
                        break;
                    default:
                }
                EXP_SUM=0;
                for(T.t=0;T.t<N_N;T.t++){
                    EXP_SUM+=ENEMY_U[T.t].CHECK_EXP();
                }
                System.out.println("你获得了"+EXP_SUM+"点经验。你的熟练度增加了，如果你足够幸运的话。");
                Player_U.EXP+=EXP_SUM;
                Player_U.PRA+=Player_U.LUCK;
                //获胜后的结算。
            }else{
                System.out.println("*****U Died*****");
            }
            READY=false;
            System.out.println("按下[Enter]以继续。");
            System.out.println("输入 0 以结束游戏。数据会丢失！");
            if(in.nextLine().equals("0")){
                CONTINUE=false;
            }
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
    }
}
