package Main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

//        int nextRow = 0, col1 = 0, col2 = 0;
//
//        if (entity.direction.equals("up")) {
//            nextRow = (entityTopWorldY - entity.speed) / gp.tileSize;
//            col1 = entityLeftCol;
//            col2 = entityRightCol;
//        } else if (entity.direction.equals("down")) {
//            nextRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
//            col1 = entityLeftCol;
//            col2 = entityRightCol;
//        } else if (entity.direction.equals("left")) {
//            nextRow = entityBottomRow; // Vertical row stays the same
//            col1 = (entityLeftWorldX - entity.speed) / gp.tileSize;
//            col2 = col1; // Both tiles are in the same column
//        } else if (entity.direction.equals("right")) {
//            nextRow = entityTopRow; // Vertical row stays the same
//            col1 = (entityRightWorldX + entity.speed) / gp.tileSize;
//            col2 = col1; // Both tiles are in the same column
//        }

//// Perform collision check
//        if (gp.tileM.tile[gp.tileM.mapTileNum[col1][nextRow]].collision ||
//                gp.tileM.tile[gp.tileM.mapTileNum[col2][nextRow]].collision) {
//            entity.collisionOn = true;
//        }

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize; //finds tile
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize; //finds tile
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize; //finds tile
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize; //finds tile
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
