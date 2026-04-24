package net.tycothepug.proximavis.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.ForgeRegistries;
import net.tycothepug.proximavis.ProximaVis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class CategoryChange {
    @Inject(method = "getCategory", at = @At("HEAD"), cancellable = true)
    private void spoofCategoryForTaggedEntities(CallbackInfoReturnable<MobCategory> cir) {
        EntityType<?> type = (EntityType<?>) (Object) this;
        ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(type);

        if (id != null && ProximaVis.GLUGG_CATEGORY_MAP.containsKey(id)) {
            cir.setReturnValue(ProximaVis.GLUGG_CATEGORY_MAP.get(id));
        }
    }
}