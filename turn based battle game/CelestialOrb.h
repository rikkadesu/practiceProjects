#ifndef CelestialOrb
#define CelestialOrb

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>

using namespace std;

void CelestialOrb_Info(int *item_selection, int *used, int items[])
{
    int equip = 0;
    system("cls");
    cout << "Name: Celestial Orb" << endl;
    cout << "Tags: Magic PEN, Magic Power" << endl;
    cout << "===============================================================" << endl;
    cout << "Description:" << endl;
    cout << "Gives +8 Magic PEN and +40 MP to the holder." << endl;
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

void CelestialOrb_Equip(hero *Fighter)
{
    Fighter->MP += 40;
    Fighter->magic_pen += 8;
}

#endif // CelestialOrb


