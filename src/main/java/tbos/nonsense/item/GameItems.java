package tbos.nonsense.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import tbos.nonsense.Nonsense;

import java.util.List;

public class GameItems {
    public static Item CRACK;

    public static final FoodComponent CRACK_FOOD_COMPOMENT = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0)
            .alwaysEdible()
            .build();

    public static final ConsumableComponent CRACK_CONS_COMPOMENT = ConsumableComponent.builder()
            .useAction(UseAction.EAT)
            .consumeSeconds(1.3f)
            .consumeParticles(true)
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            List.of (
                                    new StatusEffectInstance(StatusEffects.SPEED, 200, 21),
                                    new StatusEffectInstance(StatusEffects.STRENGTH, 200, 21),
                                    new StatusEffectInstance(StatusEffects.POISON, 200, 21),
                                    new StatusEffectInstance(StatusEffects.NAUSEA, 200, 21)
                            )
                    )
            )
            .build();

    // method to register items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Nonsense.MOD_ID, name), item);
    }

    // actually makes the items exist in menus
    public static void registerGameItems() {
        Nonsense.LOGGER.info("Registering mod items for" + Nonsense.MOD_ID);

        CRACK = registerItem("crack", new Item(
                new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Nonsense.MOD_ID,"crack")))
                    .food(CRACK_FOOD_COMPOMENT, CRACK_CONS_COMPOMENT)
        ));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CRACK);
        });
    }

}
