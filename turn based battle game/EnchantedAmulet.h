#ifndef EnchantedAmulet
#define EnchantedAmulet

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>

using namespace std;

void EnchantedAmulet_Info(int *item_selection, int *used, int items[])
{
    int equip = 0;
    system("cls");
    cout << "Name: Enchanted Amulet" << endl;
    cout << "Tags: Massive Magic Power" << endl;
    cout << "===============================================================" << endl;
    cout << "Description:" << endl;
    cout << "Gives +70 Magic Power to the holder." << endl;
    cout << "Unique: Gives 40% more Magic Power." << endl;
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

void EnchantedAmulet_Equip(hero *Fighter, float *magic_modifier)
{
    Fighter->MP += 70;
    if(*magic_modifier < 40)
        *magic_modifier = 0.3;
}

#endif // EnchantedAmulet
