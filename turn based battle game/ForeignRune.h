#ifndef ForeignRune
#define ForeignRune

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>

using namespace std;

void ForeignRune_Info(int *item_selection, int *used, int items[])
{
    int equip = 0;
    system("cls");
    cout << "Name: Foreign Rune" << endl;
    cout << "Tags: Health, Defense" << endl;
    cout << "===============================================================" << endl;
    cout << "Description:" << endl;
    cout << "Gives +200 HP and +20 Armor and Magic Resistance to the holder." << endl;
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

void ForeignRune_Equip(hero *Fighter)
{
    Fighter->MaxHP += 200;
    Fighter->HP += 200;
    Fighter->AR += 20;
    Fighter->MR += 20;
}

#endif // ForeignRune

