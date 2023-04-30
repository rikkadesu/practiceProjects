#ifndef DragonsFang
#define DragonsFang

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>

using namespace std;

void DragonsFang_Info(int *item_selection, int *used, int items[])
{
    int equip = 0;
    system("cls");
    cout << "Name: Dragon's Fang" << endl;
    cout << "Tags: Attack Damage, Critical Chance" << endl;
    cout << "===============================================================" << endl;
    cout << "Description:" << endl;
    cout << "Gives +35% Critical Strike chance and +30 AD to the holder." << endl;
    cout << "===============================================================" << endl;
    cout << "Do you like to equip this item?" << endl;
    cout << "1 - Yes, 0 - No" << endl;
    cout << "Enter selection: ";
    cin >> equip;

    if(1 == equip)
    {
        items[*used] = *item_selection;
        *used += 1;
    }
}

void DragonsFang_Equip(hero *Fighter)
{
    Fighter->AD += 30;
    Fighter->CRIT += 35;
    if(Fighter->CRIT > 100)
        Fighter->CRIT = 100;
}

#endif // DragonsFang

