package com.gmail.zendarva.parachronology.jei;

import com.gmail.zendarva.parachronology.Configuration.ConfigurationHolder;
import com.gmail.zendarva.parachronology.DisplaceListBuilder;
import com.gmail.zendarva.parachronology.Parachronology;
import com.gmail.zendarva.parachronology.handlers.DropData;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IDrawableStatic;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by James on 8/20/2017.
 */
@JEIPlugin
public class ParachronologyPlugin implements IModPlugin {
    @Override
    public void register(IModRegistry registry) {
     registry.addRecipeHandlers(new DisplaceHandler());
     registry.addRecipeCategories(new DisplaceCatagory(registry.getJeiHelpers().getGuiHelper()));

     registry.addRecipes(DisplaceListBuilder.displaceRecipes.values());

     registry.addDescription(new ItemStack(Parachronology.displacer), "Place in the world, and surround it in a 3x3 block with appropriate blocks","Throw a simple moment at it", "Enjoy!");

     //Redo this. It's ugly.
     registry.addDescription(new ItemStack(Parachronology.moment),getDropedByInfo(new ItemStack(Parachronology.moment)));
     registry.addDescription(new ItemStack(Parachronology.moment,1,1),getDropedByInfo(new ItemStack(Parachronology.moment,1,1)));
     registry.addDescription(new ItemStack(Parachronology.moment,1,2),getDropedByInfo(new ItemStack(Parachronology.moment,1,2)));
    }

    public String[] getDropedByInfo(ItemStack stack){
        ArrayList<String> result = new ArrayList<>();
        result.add("Dropped Chance:");
        for (DropData data : ConfigurationHolder.mobDrops.values()){
            if (data.momentChance > 0 && stack.getMetadata() == 1)
                result.add(data.mobName +"     " + data.momentChance +"%");
            if (data.simpleMomentChance > 0 && stack.getMetadata() == 0)
                result.add(data.mobName+ "     " + data.simpleMomentChance +"%");
            if (data.complexMomentChance > 0 && stack.getMetadata() == 2)
                result.add(data.mobName+ "     " + data.complexMomentChance +"%");
        }
        return result.toArray(new String[0]);
    }

}
