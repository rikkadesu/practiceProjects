#ifndef ShadowBlade
#define ShadowBlade

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>

using namespace std;

void ShadowBlade_Info(int *item_selection, int *used, int items[])
{
    int equip = 0;
    system("cls");
    cout << "Name: Shadow Blade" << endl;
    cout << "Tags: Armor PEN, Attack Damage" << endl;
    cout << "===============================================================" << endl;
    cout << "Description:" << endl;
    cout << "Gives +15 Armor PEN and +30 AD to the holder." << endl;
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

void ShadowBlade_Equip(hero *Fighter)
{
    Fighter->AD += 30;
    Fighter->armor_pen += 15;
}

#endif // ShadowBlade


