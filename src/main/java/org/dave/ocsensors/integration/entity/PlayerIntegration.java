package org.dave.ocsensors.integration.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.dave.ocsensors.integration.AbstractIntegration;
import org.dave.ocsensors.integration.Integrate;
import org.dave.ocsensors.integration.ScanDataList;

import java.util.List;
import java.util.stream.Collectors;

@Integrate(name = "player")
public class PlayerIntegration extends AbstractIntegration{
    @Override
    public boolean worksWith(Entity entity) {
        return entity instanceof EntityPlayer;
    }

    @Override
    public void addScanData(ScanDataList data, Entity entity) {
        super.addScanData(data, entity);

        EntityPlayer p = (EntityPlayer) entity;

        List<ItemStack> filteredMainInventory = p.inventory.mainInventory.stream().filter((s) -> !s.isEmpty()).collect(Collectors.toList());
        List<ItemStack> filteredArmorInventory = p.inventory.armorInventory.stream().filter((s) -> !s.isEmpty()).collect(Collectors.toList());

        data.add("mainInventory", filteredMainInventory);
        data.add("armorInventory", filteredArmorInventory);
        data.add("mainHandItem", p.getHeldItemMainhand());
        data.add("offHandItem", p.getHeldItemOffhand());

        data.add("bedLocation", p.getBedLocation());

        data.add("experience", p.experience);
        data.add("experienceLevel", p.experienceLevel);
        data.add("experienceTotal", p.experienceTotal);
    }
}
