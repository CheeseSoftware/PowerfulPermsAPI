package com.github.cheesesoftware.PowerfulPermsAPI;

import java.util.Date;

public class CachedGroup {
    private int id;
    private int groupId;
    private boolean negated;
    private Date expires;
    private int expireTaskId = -1;

    public CachedGroup(int id, int groupId, boolean negated, Date expires) {
        this.id = id;
        this.groupId = groupId;
        this.negated = negated;
        this.expires = expires;
    }

    public int getId() {
        return this.id;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public boolean isNegated() {
        return this.negated;
    }

    public Date getExpirationDate() {
        return expires;
    }

    public boolean willExpire() {
        return expires != null;
    }

    public boolean hasExpired() {
        return willExpire() && getExpirationDate().before(new Date());
    }

    public int getExpireTaskId() {
        return expireTaskId;
    }

    public void setExpireTaskId(int taskId) {
        this.expireTaskId = taskId;
    }

    public static boolean isSimilar(CachedGroup group, int groupId, boolean negated, Date expires) {
        if (group == null)
            return false;
        if (group.getGroupId() == groupId) {
            if (group.isNegated() == negated) {
                if (group.getExpirationDate() == null && expires == null)
                    return true;
                else if (group.getExpirationDate() == null || expires == null)
                    return false;
                return group.getExpirationDate().equals(expires);
            }
        }
        return false;
    }
}
