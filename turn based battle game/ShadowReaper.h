#ifndef ShadowReaper
#define ShadowReaper

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>

using std::cout;
using std::endl;

namespace ShadowReaper
{
    void ShadowReaper_Info(int *lock_in)
    {
        system("cls");
        cout << "Name: Shadow Reaper" << endl;
        cout << "Tags: Burst" << endl;
        cout << "===============================================================" << endl;
        cout << "Stats:" << endl;
        cout << "AD: 65 | MP: 0" << endl;
        cout << "HP: 600 | Armor: 30" << endl << "Magic Resist: 27" << endl;
        cout << "===============================================================" << endl;
        cout << "Abilities:" << endl;
        cout << "0. Basic Attack" << endl;
        cout << "   Performs basic attack dealing 65 damage." << endl;
        cout << "   Critical strikes deal 200% damage." << endl;
        cout << endl << "   Cooldown: 0 Turns" << endl;
        cout << "1. Dark Pact" << endl;
        cout << "   Fuses his hands with shadow power and then strike dealing"   << endl;
        cout << "   50 (+120% AD) physical damage." << endl;
        cout << "   Can critically strike to deal 1.5x damage."  << endl;
        cout << endl << "   Cooldown: 2 Turns" << endl << endl;
        cout << "2. Nullify" << endl;
        cout << "   Nullifies enemy's defenses, making them receive 35% more" << endl;
        cout << "   damage on next attack." << endl;
        cout << endl << "   Cooldown: 4 Turns" << endl << endl;
        cout << "3. Shadow Execute" << endl;
        cout << "   Consumes his inner life energy to deal massive amounts of" << endl;
        cout << "   damage. Consume 15% of max HP. If the enemy is below 25% HP," << endl;
        cout << "   instantly kill it, else deal 200 (+85%AD) physical damage to" << endl;
        cout << "   it." << endl;
        cout << endl << "   Cooldown: 5 Turns" << endl;
        cout << "===============================================================" << endl;
        cout << "Select this fighter?" << endl;
        cout << "1 - Yes, 0 - No" << endl;
        cout << "Enter selection: ";
        scanf("%d", lock_in);
    }

    void GiveAttribute_ShadowReaper(hero *Fighter)
    {
        Fighter->AD = 65;
        Fighter->HP = Fighter->MaxHP = 600;
        Fighter->AR = 30;
        Fighter->MR = 27;
        Fighter->CRIT = 0;
        Fighter->armor_pen = 0;
        Fighter->magic_pen = 0;
        Fighter->damage_modifier = 0;
        Fighter->enemy_extra_damage = 0;
        Fighter->immune = false;
        Fighter->stunned = false;
    }

    void ShadowReaperBasicAttack(hero *Fighter, enemy *Alien)
    {
        srand((unsigned long)time(NULL)*(unsigned long)GetTickCount());
        int damage = 0;
        int crit = rand() % 101;
        if(crit > (100 - Fighter->CRIT))
        {
            damage = (Fighter->AD * 2);
            if(Alien->hero_extra_damage > 0)
            {
                damage += (damage*Alien->hero_extra_damage);
                Alien->hero_extra_damage = 0;
            }
            damage -= (Alien->AR - Fighter->armor_pen) / 2;
            Alien->HP -= damage;
            cout << "CRITICAL!" << endl;
            cout << "Shadow Reaper used a basic attack dealing " << damage << " physical damage!" << endl;
        }
        else
        {
            damage = Fighter->AD;
            if(Alien->hero_extra_damage > 0)
            {
                damage += (damage*Alien->hero_extra_damage);
                Alien->hero_extra_damage = 0;
            }
            damage -= (Alien->AR - Fighter->armor_pen) / 2;
            Alien->HP -= damage;
            cout << "Shadow Reaper used a basic attack, dealing " << damage << " physical damage!" << endl;
        }
    }

    void DarkPact(hero *Fighter, enemy *Alien)
    {
        srand((unsigned long)time(NULL)*(unsigned long)GetTickCount());
        int damage = 0;
        int crit = rand() % 101;
        if(crit > (100 - Fighter->CRIT))
        {
            damage = (50+(Fighter->AD*1.2)) * 1.5;
            if(Alien->hero_extra_damage > 0)
            {
                damage += (damage*Alien->hero_extra_damage);
                Alien->hero_extra_damage = 0;
            }
            damage -= (Alien->AR - Fighter->armor_pen) / 2;
            Alien->HP -= damage;
            cout << "CRITICAL!" << endl;
            cout << "Shadow Reaper used Dark Pact, dealing " << damage << " physical damage!" << endl;
        }
        else
        {
            damage = 50+(Fighter->AD*1.2);
            if(Alien->hero_extra_damage > 0)
            {
                damage += (damage*Alien->hero_extra_damage);
                Alien->hero_extra_damage = 0;
            }
            damage -= (Alien->AR - Fighter->armor_pen) / 2;
            Alien->HP -= damage;
            cout << "Shadow Reaper used Dark Pact, dealing " << damage << " physical damage!" << endl;
        }
    }

    void Nullify(enemy *Alien)
    {
        Alien->hero_extra_damage += 0.35;
        cout << "Shadow Reaper used Nullify, the enemy will receive more damage from next attack." << endl;
    }

    void ShadowExecute(hero *Fighter, enemy *Alien)
    {
        int damage = 0;
        Fighter->HP = Fighter->HP - (Fighter->MaxHP*0.15);
        if(Alien->HP <= (Alien->MaxHP * 0.25))
        {
            Alien->HP = 0;
            cout << "Shadow Reaper used Shadow Execute, successfully dealing the final blow!" << endl;
            cout << "You lost " << Fighter->MaxHP * 0.15 << " HP in the process." << endl;
        }
        else
        {
            damage = 200+(Fighter->AD*0.85);
            if(Alien->hero_extra_damage > 0)
            {
                damage += (damage*Alien->hero_extra_damage);
                Alien->hero_extra_damage = 0;
            }
            damage -= (Alien->AR - Fighter->armor_pen) / 2;
            Alien->HP -= damage;

            cout << "Shadow Reaper used Shadow Execute, dealing " << damage << " physical damage!" << endl;
            cout << "You lost " << Fighter->MaxHP * 0.15 << " HP in the process." << endl;
        }
    }

    void ShadowReaperSkillset(enemy *Alien)
    {
        cout << "Select your move:" << endl;
        cout << "0. Basic Attack" << endl;
        cout << "1. Dark Pact" << endl;
        cout << "2. Nullify" << endl;
        cout << "3. Shadow Execute | [" << Alien->MaxHP * 0.25 << "]" << endl;
    }

    void shadow_reaper_hero_attack(int attack, hero *Fighter, enemy *Alien)
    {
        switch(attack)
        {
        case 0:
            {
                ShadowReaperBasicAttack(Fighter, Alien);
                break;
            }
        case 1:
            {
                DarkPact(Fighter, Alien);
                break;
            }
        case 2:
            {
                Nullify(Alien);
                break;
            }
        case 3:
            {
                ShadowExecute(Fighter, Alien);
                break;
            }
        }
    }
}
#endif // ShadowReaper
