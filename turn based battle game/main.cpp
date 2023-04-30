#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <time.h>
#include <conio.h>
#include <unistd.h>
#include <stdbool.h>

#include "dependencies.h"

using namespace std;

void hero_select(int *selection, int *lock_in);
void item_select(int *item_selection, int *used, int items[]);
void item_equip(int used, int items[], hero *Fighter);
void display_stats(hero *Fighter);
void display_stats_enemy(enemy *Alien);
void ai_attack(hero *Fighter, enemy *Alien);
bool battle(hero *Fighter, enemy *Alien);

int main()
{
    int selection = 0; int lock_in = 0; int play = 1;
    hero Fighter; enemy Alien;

    cout << "Galactic Grudge Match: Martian Melee Madness!" << endl << endl;
    //sleep(1);
    cout << "      Press any key to continue.";
    getch();

    //Hero Picking
    while(play == 1)
    {
        lock_in = 0;
        while(lock_in == 0)
        {
            system("cls");
            cout << "  Please select your fighter" << endl;
            cout << "==============================" << endl;
            cout << " 1. Shadow Reaper" << endl;
            cout << " 2. Blood Harvester" << endl;
            cout << "==============================" << endl;
            cout << " Enter selection: ";
            cin >> selection;
            hero_select(&selection, &lock_in);
        }

        //Generating Attributes
        switch(selection)
        {
        case 1:
            {
                system("cls");
                GiveAttribute_ShadowReaper(&Fighter);
                break;
            }
        case 2:
            {
                system("cls");
                GiveAttribute_BloodHarvester(&Fighter);
                break;
            }
        }

        //Item Picking
        int used = 0, items[3] = {0, 0, 0}, item_selection = 0;
        while(3 != used)
        {
            system("cls");
            cout << "   Select three items to equip" << endl;
            cout << "  Duplicate equipment is valid" << endl;
            cout << "      Items used: " << used << endl;
            cout << "=================================" << endl;
            cout << "1. Enchanted Amulet" << endl;
            cout << "2. Dragon's Fang" << endl;
            cout << "3. Celestial Orb" << endl;
            cout << "4. Foreign Rune" << endl;
            cout << "5. Shadow Blade" << endl;
            cout << "=================================" << endl;
            cout << "Enter selection: ";
            cin >> item_selection;
            item_select(&item_selection, &used, items);
        }

        //Item Equipping
        item_equip(used, items, &Fighter);

        //Generate Enemy
        GenerateEnemy_Attributes(&Alien);

        //Start Battle
        bool winner = battle(&Fighter, &Alien);

        //Conclusion
        if(winner)
        {
            system("cls");
            cout << "You have defeated the Martian Invader!" << endl << endl;
        }
        else
        {
            system("cls");
            cout << "You died. Martian Invader will conquer your homeland." << endl << endl;
        }
        cout << "Do you want to play again?" << endl;
        cout << "1 - Yes, 0 - No" << endl;
        cout << "Enter selection: ";
        cin >> play;
    }

    return 0;
}

void hero_select(int *selection, int *lock_in)
{
    switch(*selection)
    {
    case 1:
        {
            system("cls");
            ShadowReaper_Info(lock_in);
            break;
        }
    case 2:
        {
            system("cls");
            BloodHarvester_Info(lock_in);
            break;
        }
    }
}


void item_select(int *item_selection, int *used, int items[])
{
    system("cls");
    switch(*item_selection)
    {
    case 1:
        {
            //Enchanted Amulet
            EnchantedAmulet_Info(item_selection, used, items);
            break;
        }
    case 2:
        {
            //Dragon's Fang
            DragonsFang_Info(item_selection, used, items);
            break;
        }
    case 3:
        {
            //Celestial Orb
            CelestialOrb_Info(item_selection, used, items);
            break;
        }
    case 4:
        {
            //Foreign Rune
            ForeignRune_Info(item_selection, used, items);
            break;
        }
    case 5:
        {
            //Shadow Blade
            ShadowBlade_Info(item_selection, used, items);
            break;
        }
    }
}

void item_equip(int used, int items[], hero *Fighter)
{
    float magic_modifier = 0, attack_modifier = 0;

    for(int i=0; i<used; i++)
    {
        switch(items[i])
        {
        case 1:
            {
                EnchantedAmulet_Equip(Fighter, &magic_modifier);
                continue;
            }
        case 2:
            {
                DragonsFang_Equip(Fighter);
                continue;
            }
        case 3:
            {
                CelestialOrb_Equip(Fighter);
                continue;
            }
        case 4:
            {
                ForeignRune_Equip(Fighter);
                continue;
            }
        case 5:
            {
                ShadowBlade_Equip(Fighter);
                continue;
            }
        }
    }

    if(0 < magic_modifier)
        Fighter->MP = Fighter->MP * (1 + magic_modifier);
    if(0 < attack_modifier)
        Fighter->AD = Fighter->AD * (1 + attack_modifier);
}

void display_stats(hero *Fighter, enemy *Alien)
{
    cout << "AD: " << Fighter->AD << " | " << "MP: " << Fighter->MP << endl;
    cout << "Max HP: " << Fighter->MaxHP << "|" << "HP: " << Fighter->HP << endl;
    cout << "AR: " << Fighter->AR << " | " << "MR: " << Fighter->MR << endl;
    cout << "Armor PEN: " << Fighter->armor_pen << " | " << "Magic PEN: " << Fighter->magic_pen << endl;
    cout << "Crit: " << Fighter->CRIT << "% | Bonus Damage: " << Alien->hero_extra_damage << "x" << endl;
}

void display_stats_enemy(enemy *Alien)
{
    cout << "AD: " << Alien->AD << " | " << "MP: " << Alien->MP << endl;
    cout << "Max HP: " << Alien->MaxHP << " | " << "HP: " << Alien->HP << endl;
    cout << "AR: " << Alien->AR << " | " << "MR: " << Alien->MR << endl;
    cout << "Armor PEN: " << Alien->armor_pen << " | " << "Magic PEN: " << Alien->magic_pen << endl;
    cout << "Crit: " << Alien->CRIT << "%" << endl;
}

void ai_attack(hero *Fighter, enemy *Alien)
{
    int enemy_attack = rand() % 3;

    switch(enemy_attack)
    {
    case 0:
        {
            enemy_basic_attack(Fighter, Alien);
            break;
        }
    case 1:
        {
            GalacticLaser(Fighter, Alien);
            break;
        }
    case 2:
        {
            MeteorStrike(Fighter, Alien);
            break;
        }
    }
}

bool battle(hero *Fighter, enemy *Alien)
{
    int attack = 0;

    while(Fighter->HP > 0 && Alien->HP > 0)
    {
        system("cls");
        cout << "Enemy stats:" << endl;
        display_stats_enemy(Alien);
        cout << "==========================================" << endl;
        cout << "Your stats:" << endl;
        display_stats(Fighter, Alien);
        cout << "==========================================" << endl;
        ShadowReaper::ShadowReaperSkillset(Alien);
        cout << "Enter selection: ";
        cin >> attack;
        ShadowReaper::shadow_reaper_hero_attack(attack, Fighter, Alien);
        cout << "Press any key to continue." << endl << endl;
        getch();
        if(Alien->HP > 0)
        {
            ai_attack(Fighter, Alien);
            cout << "Press any key to continue." << endl << endl;
            getch();
        }
    }
    if(Alien->HP <= 0) return true;
    else return false;
}


