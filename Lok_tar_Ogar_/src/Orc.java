import java.util.Scanner;
class Tools{
    int t;
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
class Outfit{
    String Name;int WEIGHT,SHARPNESS,PROTECTION,SPLASH;
}
class Zero extends  Outfit{
    {
        Name="从零开始";WEIGHT=0;SHARPNESS=0;PROTECTION=0;SPLASH=0;
    }
}
class Frostmourne extends Outfit{
    {
        Name="霜之哀伤";WEIGHT=3;SHARPNESS=3;PROTECTION=2;SPLASH=1;
    }
}
class JK extends Outfit{
    {
        Name="JK制服";SPLASH=10;
    }
}
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
    }//int SEQUENCE_Player,int SEQUENCE_N_P_C,String Player_Name,String N_P_C_Name,int User,Tools T,int Player_ATK_U,int N_P_C_ATK_U
    void TRIGGER(int SEQUENCE_Player,int SEQUENCE_N_P_C,ENEMY N_P_C_U,Player Player_U,int User,Tools T){
        if(SEQUENCE_Player>=100&&User==0) {
            T.t -= N_P_C_U.CHECK_ATK_U() / 5;
            if (T.t < 0) {
                T.t = 0;
            }
            System.out.println("***"+N_P_C_U.CHECK_Name()+"的技能 "+Name+" 抵挡了"+N_P_C_U.CHECK_ATK_U() / 5+"点伤害。");
        }
        if(SEQUENCE_N_P_C>=100&&User==1){
            T.t-=Player_U.ATK_U/5;
            if (T.t < 0) {
                T.t = 0;
            }
            System.out.println("***"+Player_U.Name+"的技能 "+Name+" 抵挡了"+Player_U.ATK_U / 5+"点伤害。");
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
        if(SEQUENCE_N_P_C>=100&&User==0){
            Player_U.AGI_B-=N_P_C_U.CHECK_ATK_U()/3;
            System.out.println("***"+Player_U.Name+"身上变得黏黏的了。总敏捷下降。");
        }
    }
}
class Player{
    Tools T=new Tools();
    String Name;
    boolean ALIVE;
    int EXP,LEVEL;
    int VIT,ATK,DEF,AGI,LUCK;
    int PRA;
    int ATK_B,DEF_B,AGI_B;
    int RED_MAX,ATK_U,DEF_U,AGI_U;
    int RED,YELLOW;
    {
        LEVEL=1;
    }
    Player(String Na){
        Name=Na;
    }
    Outfit Outfit_E;
    Skill Skill_E;
    void REFRESH(){
        RED_MAX=(int)(VIT*3*LEVEL*(1+PRA/100.0));
        ATK_U=(int)(ATK*LEVEL*(1+PRA/50.0)+Outfit_E.SHARPNESS*(ATK_B/100.0));
        DEF_U=(int)(DEF*LEVEL*(1+PRA/50.0)+Outfit_E.PROTECTION*(DEF_B/100.0));
        AGI_U=(int)(AGI*LEVEL*(1+PRA/50.0)-Outfit_E.WEIGHT*(AGI_B/100.0));
    }
    void RECOVER(){
        ALIVE=true;
        ATK_B=100;DEF_B=100;AGI_B=100;
        REFRESH();
        RED=RED_MAX;
    }
    int CHECK_EXP_N(){
        int EXP_N;
        switch(LEVEL){
            case 1:
                EXP_N=10;
                break;
            case 2:
                EXP_N=50;
                break;
            case 3:
                EXP_N=200;
                break;
            case 4:
                EXP_N=500;
                break;
            default:
                EXP_N=211352;
        }
        return EXP_N;
    }
    void LEVEL_UP(){
        if(LEVEL<5&&EXP>=CHECK_EXP_N()){
            int RED_DFF=RED_MAX-RED;
            EXP-=CHECK_EXP_N();
            LEVEL++;
            REFRESH();
            RED=RED_MAX-RED_DFF;
            System.out.println("现在你"+LEVEL+"级了！");
        }else if(LEVEL==5){
            System.out.println("你已经满级了！");
        }else{
            System.out.println("经验值不足！");
        }
    }
    void CHECK(){
        System.out.println("#你的信息：");
        System.out.println(Name+"~经验="+EXP+" 等级="+LEVEL+" 耐力="+VIT+" 攻击="+ATK+" 防御="+DEF+" 敏捷="+AGI+" 熟练度="+PRA);
        System.out.println("装备 "+Outfit_E.Name+"~重量="+Outfit_E.WEIGHT+" 锋利="+Outfit_E.SHARPNESS+" 保护="+Outfit_E.PROTECTION+" 溅射="+Outfit_E.SPLASH);
        System.out.println("技能 "+Skill_E.Name+"~"+Skill_E.Description);
        System.out.print("当前血量"+RED+"/"+RED_MAX);
        T.SHOW_RED(RED,RED_MAX);
        System.out.print('\n');
    }
    void RESET() {
        System.out.println("当前：耐力=" + VIT + " 攻击=" + ATK + " 防御=" + DEF + " 敏捷=" + AGI);
        Scanner in = new Scanner(System.in);
        System.out.println("幸运=25-耐力-攻击-防御-敏捷");
        System.out.println("显然，属性之和<25");
        System.out.print("耐力 攻击 防御 敏捷：");
        int VI, AT, DE, AG;
        do {
            VI = in.nextInt();
            AT = in.nextInt();
            DE = in.nextInt();
            AG = in.nextInt();
        } while (VI + AT + DE + AG > 25);
        VIT = VI;
        ATK = AT;
        DEF = DE;
        AGI = AG;
        LUCK = 25 - VI - AT - DE - AG;
    }
}
class N_P_C{
    String Name;
    boolean ALIVE;
    int LEVEL;
    int VIT,ATK,DEF,AGI;
    int AGI_B;
    int RED_MAX,ATK_U,DEF_U,AGI_U;
    int RED,YELLOW;
    int EXP;
    void REFRESH(){
        RED_MAX=VIT*3*LEVEL;
        ATK_U=ATK*LEVEL;DEF_U=DEF*LEVEL;AGI_U=(int)(AGI*LEVEL*(AGI_B/100.0));
    }
    void RECOVER(){
        AGI_B=100;
        ALIVE=true;
        REFRESH();
        RED=RED_MAX;
    }
    Skill Skill_E;
}
class Soldier extends N_P_C implements ENEMY{
    {
        Skill_E=new Shield();
        Name="守卫";LEVEL=1;VIT=3;ATK=8;DEF=2;AGI=8;EXP=5;
        RECOVER();
    }
    public void BE_ATTACKED(int VALUE){
        RED-=VALUE;
    }
    public boolean IS_ALIVE(){
        if(RED<=0){
            ALIVE=false;
        }
        return ALIVE;
    }
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
    }
}
class Slime extends N_P_C implements ENEMY{
    {
        Skill_E=new Slime_n();
        Name="史莱姆";LEVEL=2;VIT=3;ATK=9;DEF=3;AGI=3;EXP=10;
        RECOVER();
    }
    public void BE_ATTACKED(int VALUE){
        RED-=VALUE;
    }
    public boolean IS_ALIVE(){
        if(RED<=0){
            ALIVE=false;
        }
        return ALIVE;
    }
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
    }
}
interface ENEMY{
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
public class Orc {
    public static void main(String[] args){
        int EXP_SUM;
        boolean LAST_SEQUENCE;
        int N_P_C_TURN=0;
        int N_P_C_RED_MIN;
        boolean IS_SEQUENCE_N_P_C;
        int SEQUENCE_Player;
        int[] SEQUENCE_N_P_C;
        boolean IS_ALIVE;
        boolean[] BINGO_O=new boolean[4];
        boolean[] BINGO_S=new boolean[4];
        ENEMY[] ENEMY_U=null;
        int N_N=0;
        int LEVEL;
        Tools T=new Tools();
        boolean CONTINUE=true;
        boolean READY=false;
        Outfit[] Outfits=new Outfit[5];
        Outfits[0]=new Zero();
        int O_EMPTY=1;
        Skill[] Skills=new Skill[5];
        Skills[0]=new NULL();
        int S_EMPTY=1;
        Scanner in=new Scanner(System.in);
        System.out.print("你的名字：");
        Player Player_U=new Player(in.nextLine());
        Player_U.Outfit_E=Outfits[0];
        Player_U.Skill_E=Skills[0];
        Player_U.RESET();
        Player_U.RECOVER();
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        Player_U.CHECK();
        while(CONTINUE){
            while(!READY){

                do{
                    System.out.println("#主界面");
                    System.out.println("1.Lok'tar Ogar(战斗)！  2.你的信息  3.你的装备  4.你的技能  5.祈祷  6.结束  7.先来这里看看");
                    T.t=in.nextInt();
                }while(!(T.t>=1&&T.t<=7));
                switch (T.t) {
                    case 1:
                        if(Player_U.ALIVE) {
                            READY=true;
                        }else {
                            System.out.println("你已无生命特征。请在主界面祈祷以复活。");
                        }
                        break;
                    case 2:
                        Player_U.CHECK();
                        if(Player_U.ALIVE) {
                            do {
                                System.out.println("1.如果经验足够，就可以进行“升级”了。当前需要" + Player_U.CHECK_EXP_N() + "点经验。");
                                System.out.println("2.想洗点吗？需要花费" + Player_U.LEVEL * Player_U.LEVEL * 20 + "点经验。");
                                System.out.println("3.再见。");
                                T.t = in.nextInt();
                            } while (!(T.t >= 1 && T.t <= 3));
                            switch (T.t) {
                                case 1:
                                    Player_U.LEVEL_UP();
                                    break;
                                case 2:
                                    if (Player_U.EXP >= Player_U.LEVEL * Player_U.LEVEL * 20) {
                                        Player_U.EXP -= Player_U.LEVEL * Player_U.LEVEL * 20;
                                        Player_U.RESET();
                                    } else {
                                        System.out.println("经验值不足！");
                                    }
                                    break;
                                case 3:
                                    break;
                            }
                        }else {
                            System.out.println("你已无生命特征。请在主界面祈祷以复活。");
                        }
                        break;
                    case 3:
                        for(O_EMPTY=0;Outfits[O_EMPTY]!=null;O_EMPTY++);
                        T.t=0;
                        while(Outfits[T.t]!=null){
                            System.out.println(T.t+"."+Outfits[T.t].Name);
                            T.t++;
                        }
                        do {
                            System.out.println("输入序号即可装备。在“你的信息”中可查看装备详情。");
                            T.t = in.nextInt();
                        } while (!(T.t >= 0 && T.t < O_EMPTY));
                        Player_U.Outfit_E=Outfits[T.t];
                        Player_U.REFRESH();
                        break;
                    case 4:
                        for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);
                        T.t=0;
                        while(Skills[T.t]!=null){
                            System.out.println(T.t+"."+Skills[T.t].Name);
                            T.t++;
                        }
                        do {
                            System.out.println("输入序号即可携带。在“你的信息”中可查看技能详情。");
                            T.t = in.nextInt();
                        } while (!(T.t >= 0 && T.t < S_EMPTY));
                        Player_U.Skill_E=Skills[T.t];
                        Player_U.REFRESH();
                        break;
                    case 5:
                        do{
                            System.out.println("你可以花费"+Player_U.LEVEL*Player_U.LEVEL*20+"点经验以完全恢复。");
                            System.out.println("1.确定  2.取消");
                            T.t=in.nextInt();
                        }while(!(T.t>=1&&T.t<=2));
                        if(T.t==1){
                            if (Player_U.EXP >= Player_U.LEVEL * Player_U.LEVEL * 20) {
                                Player_U.EXP-=Player_U.LEVEL * Player_U.LEVEL * 20;
                                Player_U.RECOVER();
                            }else{
                                System.out.println("经验值不足！");
                                System.out.println("你可以输入 I love CQUPT 以完全恢复。");
                                in.nextLine();
                                if(in.nextLine().equals("I love CQUPT")){
                                    Player_U.RECOVER();
                                }else{
                                    System.out.println("输入错误!");
                                }
                            }
                        }
                        break;
                    case 6:
                        return;
                    case 7:
                        do{
                            System.out.println("1.顺序值介绍  2.溅射介绍  3.彩蛋介绍  4.来点福利？");
                            T.t=in.nextInt();
                        }while(!(T.t>=1&&T.t<=4));
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
                                System.out.println("您的JK制服已到账。");
                                for(O_EMPTY=0;Outfits[O_EMPTY]!=null;O_EMPTY++);
                                Outfits[O_EMPTY]=new JK();
                        }
                        in.nextLine();in.nextLine();
                        break;
                }
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
            do{
                System.out.print("请选择关卡：");
                LEVEL=in.nextInt();
            }while(!(LEVEL>=1&&LEVEL<=2));
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
            }
            SEQUENCE_N_P_C=new int[N_N];
            System.out.println("你发现了敌人。");
            for(T.t=0;T.t<N_N;T.t++) {
                System.out.println(ENEMY_U[T.t].CHECK_Name() + "~血量=" + ENEMY_U[T.t].CHECK_RED_MAX() + " 总攻击=" + ENEMY_U[T.t].CHECK_ATK_U() + " 总防御=" + ENEMY_U[T.t].CHECK_DEF_U() + " 总敏捷=" + ENEMY_U[T.t].CHECK_AGI_U() + " 技能：" + ENEMY_U[T.t].CHECK_Skill_Name());
            }
            System.out.println("按下[Enter]以继续。");
            in.nextLine();in.nextLine();
            IS_ALIVE=true;
            SEQUENCE_Player=0;
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
                while(SEQUENCE_Player<100&&IS_SEQUENCE_N_P_C){
                    SEQUENCE_Player+=Player_U.AGI_U;
                    for(T.t=0;T.t<N_N;T.t++){
                        if(ENEMY_U[T.t].IS_ALIVE()) {
                            SEQUENCE_N_P_C[T.t] += ENEMY_U[T.t].CHECK_ATK_U();
                            if (SEQUENCE_N_P_C[T.t] >= 100) {
                                N_P_C_TURN=T.t;
                                IS_SEQUENCE_N_P_C = false;
                            }
                        }
                    }
                }
                if(!(SEQUENCE_Player<100&&LAST_SEQUENCE)) {
                    System.out.print(Player_U.Name + "的顺序值=" + SEQUENCE_Player + "  V.S  ");//+ENEMY_U.CHECK_Name()+"的顺序值="+SEQUENCE_N_P_C
                    for (T.t = 0; T.t < N_N; T.t++) {
                        if (ENEMY_U[T.t].IS_ALIVE()) {
                            System.out.print(ENEMY_U[T.t].CHECK_Name() + "的顺序值=" + SEQUENCE_N_P_C[T.t] + "  ");
                        }
                    }
                    System.out.print("\n");
                }
                if(SEQUENCE_Player>=100){
                    N_P_C_RED_MIN=0;
                    for(T.t=0;T.t<N_N;T.t++){
                        if(ENEMY_U[T.t].IS_ALIVE()&&(ENEMY_U[T.t].CHECK_RED()<ENEMY_U[N_P_C_RED_MIN].CHECK_RED())){
                            N_P_C_RED_MIN=T.t;
                        }
                        if(!ENEMY_U[N_P_C_RED_MIN].IS_ALIVE()){
                            N_P_C_RED_MIN++;
                        }
                    }
                    T.t=Player_U.ATK_U-ENEMY_U[N_P_C_RED_MIN].CHECK_DEF_U();
                    if(T.t<0){
                        T.t=0;
                    }
                    Player_U.Skill_E.TRIGGER(SEQUENCE_Player,SEQUENCE_N_P_C[N_P_C_RED_MIN],ENEMY_U[N_P_C_RED_MIN],Player_U,1,T);
                    ENEMY_U[N_P_C_RED_MIN].Skill(SEQUENCE_Player,SEQUENCE_N_P_C[N_P_C_RED_MIN],ENEMY_U[N_P_C_RED_MIN],Player_U,0,T);
                    System.out.println("***你的动作 "+Player_U.Name+"对"+ENEMY_U[N_P_C_RED_MIN].CHECK_Name()+"造成了"+T.t+"点伤害***");
                    ENEMY_U[N_P_C_RED_MIN].BE_ATTACKED(T.t);
                    for(T.t=0;T.t<N_N;T.t++){
                        if(T.t!=N_P_C_RED_MIN){
                            ENEMY_U[T.t].BE_ATTACKED(Player_U.Outfit_E.SPLASH);
                        }
                    }
                    SEQUENCE_Player=0;
                }else if(SEQUENCE_N_P_C[N_P_C_TURN]>=100){
                    T.t=ENEMY_U[N_P_C_TURN].CHECK_ATK_U()-Player_U.DEF_U;
                    if(T.t<0){
                        T.t=0;
                    }
                    Player_U.Skill_E.TRIGGER(SEQUENCE_Player,SEQUENCE_N_P_C[N_P_C_TURN],ENEMY_U[N_P_C_TURN],Player_U,1,T);
                    ENEMY_U[N_P_C_TURN].Skill(SEQUENCE_Player,SEQUENCE_N_P_C[N_P_C_TURN],ENEMY_U[N_P_C_TURN],Player_U,0,T);
                    System.out.println("***对方动作 "+ENEMY_U[N_P_C_TURN].CHECK_Name()+"对"+Player_U.Name+"造成了"+T.t+"点伤害***");
                    Player_U.RED-=T.t;
                    SEQUENCE_N_P_C[N_P_C_TURN]=0;
                }
                System.out.print(Player_U.Name+"剩余血量："+Player_U.RED+"/"+Player_U.RED_MAX);
                T.SHOW_RED(Player_U.RED,Player_U.RED_MAX);
                System.out.print('\n');
                for(T.t=0;T.t<N_N;T.t++) {
                    System.out.print(ENEMY_U[T.t].CHECK_Name() + "剩余血量：" + ENEMY_U[T.t].CHECK_RED() + "/" + ENEMY_U[T.t].CHECK_RED_MAX());
                    T.SHOW_RED(ENEMY_U[T.t].CHECK_RED(), ENEMY_U[T.t].CHECK_RED_MAX());
                }
                System.out.print('\n');
                in.nextLine();
                if(Player_U.RED<=0){
                    Player_U.ALIVE=false;
                }
                IS_ALIVE=false;
                for(T.t=0;T.t<N_N;T.t++){
                    if(ENEMY_U[T.t].IS_ALIVE()){
                        IS_ALIVE=true;
                    }
                }
            }
            if(Player_U.ALIVE){
                System.out.println("*****U Survived*****");
                for(O_EMPTY=0;Outfits[O_EMPTY]!=null;O_EMPTY++);
                for(S_EMPTY=0;Skills[S_EMPTY]!=null;S_EMPTY++);
                switch (LEVEL){
                    case 1:
                        if(!BINGO_O[0]) {
                            Outfits[O_EMPTY] = new Frostmourne();
                            System.out.println(">>>霜之哀伤？怎么会在这儿？……");
                            System.out.println(">>>你获得了一件装备。");
                            BINGO_O[0]=true;
                        }
                        if(!BINGO_S[0]) {
                            if (Player_U.Skill_E.Name.equals("从无到有")) {
                                Skills[S_EMPTY] = new Shield();
                                System.out.println(">>>你的技能 从无到有 发动。");
                                System.out.println(">>>你获得了一个技能。");
                                BINGO_S[0] = true;
                            }
                        }
                        break;
                    case 2:
                        if(!BINGO_S[1]) {
                            if (Player_U.Skill_E.Name.equals("从无到有")) {
                                Skills[S_EMPTY] = new Slime_n();
                                System.out.println(">>>你的技能 从无到有 发动。");
                                System.out.println(">>>你获得了一个技能。");
                                BINGO_S[1] = true;
                            }
                        }
                        break;
                }
                EXP_SUM=0;
                for(T.t=0;T.t<N_N;T.t++){
                    EXP_SUM+=ENEMY_U[T.t].CHECK_EXP();
                }
                System.out.println("你获得了"+EXP_SUM+"点经验，你的熟练度增加了。");
                Player_U.EXP+=EXP_SUM;
                Player_U.PRA++;
                in.nextLine();
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
