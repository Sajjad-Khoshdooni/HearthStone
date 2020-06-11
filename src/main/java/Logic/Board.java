package Logic;

import java.util.ArrayList;
import java.util.List;

public class Board{
    private List<Card> cards=new ArrayList<Card>();
    private List<Hero> heros=new ArrayList<Hero>();
    private Player player=new Player();
//    private String cardSkinUrl = congif.getskin;

    //getter and setter
    public Board(){ init();}

  //  public String getCardSkinUrl() {
    //    return cardSkinUrl;
//    }

  //  public void setCardSkinUrl(String cardSkinUrl) {
    //    this.cardSkinUrl = cardSkinUrl;
   // }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Hero> getHeros() {
        return heros;
    }

    public void setHeros(List<Hero> heros) {
        this.heros = heros;
    }

    public void init(){
        cardGiveBirth();
        lockCards();
        heroGiveBirth();
    }

    private void cardGiveBirth(){

        //baray card ha yek field misazim ta speacial work ro bege

        // HERO CARDS SECTION-Logic.Mage
        Card polymorph=new Spell("Polymorph",4,"Free","Mage","Spell","Transform a minion into 1/1 sheep.",11);
        cards.add(polymorph);

        Card fireball=new Spell("Fireball",4,"Free","Mage","Spell","Deal 6 damage.",11);
        cards.add(fireball);

        // HERO CARDS SECTION-Logic.Rouge
        Card friendlySmith = new Spell("FriendlySmith",1,"Free","Rouge","Spell","Discover a weapon from any class.Add it to your Adventure Deck with 2/2",11);
        cards.add(friendlySmith);

        Card sinisterStrike=new Spell("SinisterStrike",1,"Free","Rouge","Spell","Deal 3 damage to enemy hero.",11);
        cards.add(sinisterStrike);

        // HERO CARDS SECTION-Logic.Warlock
        Card dreadscale=new Minion("Dreadscale",3,"Legendary","Warlock","Minion","At the end of your turn, deal 1 damage to all other minions.",2,4,11);
        cards.add(dreadscale);

        Card highPriestessJeklik=new Minion("HighPriestessJeklik",4,"Legendary","Warlock","Minion","Taunt, Lifesteal When you discard this,add 2 copies of it to your hand",4,3,11);
       cards.add(highPriestessJeklik);

          // HERO CARDS SECTION-HUNTER
          Card swampKingDred = new Minion("SwampKingDred",7,"Free","Hunter","Minion","After your opponent plays a minion,attack it",9,9,11);
          cards.add(swampKingDred);

          Card deadlyShot = new Spell("DeadlyShot",3,"Free","Hunter","Spell","Destroy a random enemy minion",11);
          cards.add(deadlyShot);

          // HERO CARDS SECTION-PRIEST
          Card highPriestAmet = new Minion("HighPriestAmet",4,"Free","Priest","Minion","Whenever you summon a minion set its health equal to this minions",7,2,11);
          cards.add(highPriestAmet);

          Card penance = new Spell("Penance",2,"Free","Priest","Spell","Lifesteal deal 3 damage to a minion",11);
          cards.add(penance);


        // Create natural cards
        //Minions
        Card GoldshireFootman=new Minion("GoldshireFootman",1,"Free","Neutral","Minion","Taunt",2,1,11);
        cards.add(GoldshireFootman);

        Card  NoviceEngineer= new Minion("NoviceEngineer",2,"Free","Neutral","Minion","Battlecry: Draw a card.",1,1,11);
        cards.add(NoviceEngineer);

        Card StormwindKnight =new Minion("StormwindKnight",4,"Free","Neutral","Minion","Charge",5,2,11);
        cards.add(StormwindKnight);

        Card WindfuryHarpy=new Minion("WindfuryHarpy",6,"Common","Neutral","Minion","Windfury",5,4,11);
        cards.add(WindfuryHarpy);

        Card CoreHound=new Minion("CoreHound",7,"Free","Neutral","Minion","",5,9,11);
        cards.add(CoreHound);

        //phase 2
        Card Sathrovarr = new Minion("Sathrovarr",9,"Free","Neutral","Minion","Battlecry: Choose a friendly minion.add a copy of it to your hand,deck and battlefield",5,5,11);
        cards.add(Sathrovarr);

        Card TombWarden = new Minion("TombWarden",8,"Free","Neutral","Minion","Taunt. Battlecry: Summon a copy of this minion",6,3,11);
        cards.add(TombWarden);

        Card SecurityRover = new Minion("SecurityRover",6,"Free","Neutral","Minion","Whenever this minion takes damage, summon a 2/3 Mech with Taunt",6,2,11);
        cards.add(SecurityRover);

        Card CurioCollecter = new Minion("CurioCollector",5,"Free","Neutral","Minion","Whenever you draw a card gain 1/1",4,4,11);
        cards.add(CurioCollecter);



        // Create natural cards
        // Logic.Spell
        Card HealingTouch =new Spell("HealingTouch",3,"Free","Neutral","Spell","Restore 8 health.",11);
        cards.add(HealingTouch);

        Card DeadlyShot=new Spell("DeadlyShot",3,"Common","Neutral","Spell","Destroy a random enemy minion.",11);
        cards.add(DeadlyShot);

        Card FrostNova=new Spell("FrostNova",3,"Free","Neutral","Spell","Freeze all enemy minions.",11);
        cards.add(FrostNova);

        Card Pyroblast=new Spell("Pyroblast",10,"Epic","Neutral","Spell","Deal 10 damage.",11);
        cards.add(Pyroblast);

        Card ArcaneIntellect =new Spell("ArcaneIntellect",3,"Free","Neutral","Spell","Draw 2 cards.",11);
        cards.add(ArcaneIntellect);

        //phase 2
        Card Sprint = new Spell("Sprint",7,"Free","Neutral","Spell","Draw 4 cards",11);
        cards.add(Sprint);

        Card Swarmoflocusts = new Spell("Swarmoflocusts",6,"Free","Neutral","Spell","Summon seven 1/1 Locusts with Rush",11);
        cards.add(Swarmoflocusts);

        Card PharaohsBlessing = new Spell("PharaohsBlessing",6,"Free","Neutral","Spell","Give a Minion 4/4 Divine shield,and Taunt",11);
        cards.add(PharaohsBlessing);

        Card BookofSpecters = new Spell("BookofSpecters",2,"Free","Neutral","Spell","Draw 3 cards.Discard any spell drawn",11);
        cards.add(BookofSpecters);



        //create weapon Logic.Card
        Card BloodClaw=new Weapon("Bloodclaw",1,"Common","Neutral","Weapon","Battlecry: Deal 5 damage to your hero.",2,2,11);
        cards.add(BloodClaw);

        Card StormforgedAxe=new Weapon("StormforgedAxe",2,"Common","Neutral","Weapon","Overload: (1)",3,2,11);
        cards.add(StormforgedAxe);

        Card BloodFury = new Weapon("BloodFury",3,"Free","Neutral","Weapon","",8,3,11);
        cards.add(BloodFury);

        Card DragonClaw = new Weapon("DragonClaw",5,"Free","Neutral","Weapon","",2,5,11);
        cards.add(DragonClaw);

        Card WickedKnife = new Weapon("WickedKnife",1,"Free","Neutral","Weapon","",2,1,11);
        cards.add(WickedKnife);

 //       Quest and Reward Cards;

        // card tarahi khodm --> emtiazie faz 1
        Card White=new Spell("white",5,"Free","Neutral","Spell","HeroPower birthday.",11);
        cards.add(White);
    }

    private void lockCards(){
        for (Card card:cards){
            if (card.getCardName().toLowerCase().equals("white")) card.lock();
            else if (card.getCardName().toLowerCase().equals("dragonclaw")) card.lock();
            else if (card.getCardName().toLowerCase().equals("sprint")) card.lock();
            else if (card.getCardName().toLowerCase().equals("frostnova")) card.lock();
            else if (card.getCardName().toLowerCase().equals("tombwarden")) card.lock();
            else if (card.getCardName().toLowerCase().equals("corehound")) card.lock();
        }
    }

    private void heroGiveBirth(){
        Hero mage=new Mage("Mage");
        Hero warlock=new Warlock("Warlock");
        Hero rouge=new Rouge("Rouge");
        Hero hunter =new Hunter("Hunter");
        Hero priest = new Priest("Priest");

        mage.setFreedom(true);

        //add heroes special cards here|||
        for (Card spcard : cards) {
            if (spcard.getCardClass().toLowerCase().equals("mage")) {
                mage.setCard(spcard);
            }
        }
        for (Card spcard : cards) {
            if (spcard.getCardClass().toLowerCase().equals("warlock")) {
                warlock.setCard(spcard);
            }
        }
        for (Card spcard : cards) {
            if (spcard.getCardClass().toLowerCase().equals("rouge")) {
                rouge.setCard(spcard);
            }
        }
        for (Card spcard : cards) {
            if (spcard.getCardClass().toLowerCase().equals("hunter")) {
                hunter.setCard(spcard);
            }
        }
        for (Card spcard : cards) {
            if (spcard.getCardClass().toLowerCase().equals("priest")) {
                priest.setCard(spcard);
            }
        }

        heros.add(mage);
        heros.add(warlock);
        heros.add(rouge);
        heros.add(hunter);
        heros.add(priest);
    }
}