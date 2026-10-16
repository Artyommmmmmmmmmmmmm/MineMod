package com.example.seregamod.block.custom;

import com.example.seregamod.block.ModBlocks;
import com.example.seregamod.block.entity.SpreaderBlockEntity;
import com.example.seregamod.util.ModTags;
import com.mojang.authlib.properties.Property;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TumorBlock extends Block{
//    public boolean canInfest = true;
    public TumorBlock(Properties properties) {
        super(properties);

    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
//        if (!canInfest) return;
        int newEnergy = 500;
        boolean airNear = false;
        BlockState NUState = Blocks.AIR.defaultBlockState();
        BlockState NDState = Blocks.AIR.defaultBlockState();
        BlockState SUState = Blocks.AIR.defaultBlockState();
        BlockState SDState = Blocks.AIR.defaultBlockState();
        BlockState EUState = Blocks.AIR.defaultBlockState();
        BlockState EDState = Blocks.AIR.defaultBlockState();
        BlockState WUState = Blocks.AIR.defaultBlockState();
        BlockState WDState = Blocks.AIR.defaultBlockState();
        BlockState ESState = Blocks.AIR.defaultBlockState();
        BlockState ENState = Blocks.AIR.defaultBlockState();
        BlockState WSState = Blocks.AIR.defaultBlockState();
        BlockState WNState = Blocks.AIR.defaultBlockState();
        BlockState NState = Blocks.AIR.defaultBlockState();
        BlockState SState = Blocks.AIR.defaultBlockState();
        BlockState EState = Blocks.AIR.defaultBlockState();
        BlockState WState = Blocks.AIR.defaultBlockState();
        BlockState UState = Blocks.AIR.defaultBlockState();
        BlockState DState = Blocks.AIR.defaultBlockState();
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = level.getBlockState(neighborPos);
            if (!neighborState.isAir()) {
                switch(direction) {
                    case EAST:
                        BlockPos EU = neighborPos.relative(Direction.UP);
                        EUState = level.getBlockState(EU);
                        BlockPos ED = neighborPos.relative(Direction.DOWN);
                        EDState = level.getBlockState(ED);
                        BlockPos ES = neighborPos.relative(Direction.SOUTH);
                        ESState = level.getBlockState(ES);
                        BlockPos EN = neighborPos.relative(Direction.NORTH);
                        ENState = level.getBlockState(EN);
                        EState = neighborState;
                        break;
                    case WEST:
                        BlockPos WU = neighborPos.relative(Direction.UP);
                        WUState = level.getBlockState(WU);
                        BlockPos WD = neighborPos.relative(Direction.DOWN);
                        WDState = level.getBlockState(WD);
                        BlockPos WS = neighborPos.relative(Direction.SOUTH);
                        WSState = level.getBlockState(WS);
                        BlockPos WN = neighborPos.relative(Direction.NORTH);
                        WNState = level.getBlockState(WN);
                        WState = neighborState;
                        break;
                    case NORTH:
                        BlockPos NU = neighborPos.relative(Direction.UP);
                        NUState = level.getBlockState(NU);
                        BlockPos ND = neighborPos.relative(Direction.DOWN);
                        NDState = level.getBlockState(ND);
                        NState = neighborState;
                        break;
                    case SOUTH:
                        BlockPos SU = neighborPos.relative(Direction.UP);
                        SUState = level.getBlockState(SU);
                        BlockPos SD = neighborPos.relative(Direction.DOWN);
                        SDState = level.getBlockState(SD);
                        SState = neighborState;
                        break;
                    case UP:
                        UState = neighborState;
                        break;
                    case DOWN:
                        DState = neighborState;
                        break;
                }
            }
        }
        boolean airHorizontal = SState.isAir() || NState.isAir() ||EState.isAir() || WState.isAir();
        boolean airUpDown= UState.isAir() || DState.isAir();
        boolean moveUp = NUState.isAir() ||  SUState.isAir() || EUState.isAir() || WUState.isAir() || airHorizontal;
        boolean moveDown = NDState.isAir() ||  SDState.isAir() || EDState.isAir() || WDState.isAir() || airHorizontal;
        boolean moveNorth = EState.isAir() ||  WState.isAir() || NUState.isAir() || NDState.isAir() ||
                ENState.isAir() || WNState.isAir() || airUpDown;
        boolean moveSouth = EState.isAir() ||  WState.isAir() || SUState.isAir() || SDState.isAir() ||
                ESState.isAir() || WSState.isAir() || airUpDown;
        boolean moveEast = SState.isAir() ||  NState.isAir() || EUState.isAir() || EDState.isAir() ||
                ENState.isAir() || ESState.isAir() || airUpDown;
        boolean moveWest = SState.isAir() ||  NState.isAir() || WUState.isAir() || WDState.isAir() ||
                WNState.isAir() || WSState.isAir() || airUpDown;
        for (Direction direction : Direction.values()) {

            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = level.getBlockState(neighborPos);
            if (!neighborState.isAir() && !neighborState.is(ModTags.Blocks.SATURN_TAG)) {
                if (direction == Direction.UP && moveUp) {
                    level.setBlock(neighborPos, ModBlocks.SPREADER_BLOCK.get().defaultBlockState().setValue(SpreaderBlock.ENERGY, newEnergy), 3);
                    if (level.getBlockEntity(neighborPos) instanceof SpreaderBlockEntity spreader) {
                        spreader.setStoredBlock(pos);
                    }
                } else if (direction == Direction.DOWN && moveDown) {
                    level.setBlock(neighborPos, ModBlocks.SPREADER_BLOCK.get().defaultBlockState().setValue(SpreaderBlock.ENERGY, newEnergy), 3);
                    System.out.println(level.getBlockEntity(neighborPos));
                    if (level.getBlockEntity(neighborPos) instanceof SpreaderBlockEntity spreader) {
                        System.out.println("AAAAAAAAAAAAAAAA");
                        spreader.setStoredBlock(pos);
                    }
                } else if (direction == Direction.NORTH && moveNorth) {
                    level.setBlock(neighborPos, ModBlocks.SPREADER_BLOCK.get().defaultBlockState().setValue(SpreaderBlock.ENERGY, newEnergy), 3);
                    if (level.getBlockEntity(neighborPos) instanceof SpreaderBlockEntity spreader) {
                        spreader.setStoredBlock(pos);
                    }
                } else if (direction == Direction.SOUTH && moveSouth) {
                    level.setBlock(neighborPos, ModBlocks.SPREADER_BLOCK.get().defaultBlockState().setValue(SpreaderBlock.ENERGY, newEnergy), 3);
                    if (level.getBlockEntity(neighborPos) instanceof SpreaderBlockEntity spreader) {
                        spreader.setStoredBlock(pos);
                    }
                } else if (direction == Direction.EAST && moveEast) {
                    level.setBlock(neighborPos, ModBlocks.SPREADER_BLOCK.get().defaultBlockState().setValue(SpreaderBlock.ENERGY, newEnergy), 3);
                    if (level.getBlockEntity(neighborPos) instanceof SpreaderBlockEntity spreader) {
                        spreader.setStoredBlock(pos);
                    }
                } else if (direction == Direction.WEST && moveWest) {
                    level.setBlock(neighborPos, ModBlocks.SPREADER_BLOCK.get().defaultBlockState().setValue(SpreaderBlock.ENERGY, newEnergy), 3);
                    if (level.getBlockEntity(neighborPos) instanceof SpreaderBlockEntity spreader) {
                        spreader.setStoredBlock(pos);
                    }
                }

            }
        }
//        this.canInfest = false;
    }
    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        level.scheduleTick(pos, this, 2);
        super.onPlace(state, level, pos, oldState, movedByPiston);
    }
}
